package com.zhenhui.sparkler.data.repository.impl;

import com.zhenhui.sparkler.data.model.core.post.Category;
import com.zhenhui.sparkler.data.model.core.post.Post;
import com.zhenhui.sparkler.data.model.core.post.PostStats;
import com.zhenhui.sparkler.data.model.core.post.Section;
import com.zhenhui.sparkler.data.model.mapper.PostMapper;
import com.zhenhui.sparkler.data.model.mapper.PostStatsMapper;
import com.zhenhui.sparkler.data.repository.PostRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Post createPost(Post post, Section section, Long number) {

        postMapper.insert(post);

        PostStats stats = new PostStats(section.getCategoryId(), section.getId(), number, post.getId());
        statsMapper.insert(stats);

        return post;
    }

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
            Map<Long, Post> postMap = postMapper.selectByIds(postIds).stream()
                    .collect(Collectors.toMap(Post::getId, Function.identity()));
            stats.forEach(s -> {
                if (postMap.containsKey(s.getPostId())) {
                    result.add(postMap.get(s.getPostId()));
                }
            });
        }

        return result;
    }
}



