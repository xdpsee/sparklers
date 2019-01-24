package com.zhenhui.sparkler.data.repository.impl;

import com.google.common.collect.Sets;
import com.zhenhui.sparkler.data.model.core.post.Category;
import com.zhenhui.sparkler.data.model.core.post.Post;
import com.zhenhui.sparkler.data.model.core.post.PostStats;
import com.zhenhui.sparkler.data.model.core.post.Section;
import com.zhenhui.sparkler.data.model.mapper.PostMapper;
import com.zhenhui.sparkler.data.model.mapper.PostStatsMapper;
import com.zhenhui.sparkler.data.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.cache.Cache;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PostRepositoryImpl implements PostRepository {

    @Resource
    private PostMapper postMapper;
    @Resource
    private PostStatsMapper statsMapper;

    @Autowired
    private Cache<Long, Post> postCache;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Post createPost(Post post, Section section, Long number) {

        postMapper.insert(post);

        PostStats stats = new PostStats(section.getCategoryId(), section.getId(), number, post.getId());
        statsMapper.insert(stats);

        return post;
    }

    @Cacheable(cacheNames = "postCache", unless = "#result==null")
    @Override
    public Post queryPost(long postId) {
        return postMapper.selectById(postId);
    }

    @Override
    public List<Post> queryPosts(Section section, long startNo, int count) {

        final List<PostStats> stats = statsMapper.selectByCategorySection(section.getCategoryId(), section.getId(), startNo, count);

        return queryPosts(stats);
    }

    @Override
    public List<Post> queryPosts(Category category, long startNo, int count) {
        final List<PostStats> stats = statsMapper.selectByCategory(category.getId(), startNo, count);

        return queryPosts(stats);
    }

    private List<Post> queryPosts(List<PostStats> stats) {

        final List<Post> result = new ArrayList<>();

        List<Long> postIds = stats.stream()
                .map(PostStats::getPostId)
                .collect(Collectors.toList());

        if (!postIds.isEmpty()) {
            final Map<Long, Post> postMap = loadPostsFromCache(postIds);

            postIds.removeAll(new ArrayList<>(postMap.keySet()));

            final Map<Long,Post> absentPosts = postMapper.selectByIds(postIds).stream()
                    .collect(Collectors.toMap(Post::getId, Function.identity()));
            postMap.putAll(absentPosts);

            savePostsToCache(absentPosts);

            stats.forEach(s -> {
                if (postMap.containsKey(s.getPostId())) {
                    result.add(postMap.get(s.getPostId()));
                }
            });
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private Map<Long, Post> loadPostsFromCache(List<Long> postIds) {
        if (postIds.isEmpty()) {
            return Collections.EMPTY_MAP;
        }

        return postCache.getAll(Sets.newHashSet(postIds));
    }

    private void savePostsToCache(Map<Long, Post> postMap) {
        if (!postMap.isEmpty()) {
            postCache.putAll(postMap);
        }
    }
}



