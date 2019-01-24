package com.zhenhui.sparkler.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhenhui.sparkler.api.domain.PostDto;
import com.zhenhui.sparkler.api.domain.PostQueryDto;
import com.zhenhui.sparkler.api.service.PostReadService;
import com.zhenhui.sparkler.data.model.core.post.Category;
import com.zhenhui.sparkler.data.model.core.post.Post;
import com.zhenhui.sparkler.data.model.core.post.Section;
import com.zhenhui.sparkler.data.repository.CategoryRepository;
import com.zhenhui.sparkler.data.repository.PostRepository;
import com.zhenhui.sparkler.data.repository.SectionRepository;
import com.zhenhui.sparkler.service.converter.PostConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(version = "1.0.0")
public class PostReadServiceImpl implements PostReadService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private PostConverter postConverter;

    @Override
    public PostDto getPost(long postId) {

        return Optional.ofNullable(postRepository.queryPost(postId))
                .filter(Objects::nonNull)
                .map(postConverter::to)
                .orElseGet(null);
    }

    @Override
    public List<PostDto> listPost(PostQueryDto query) {

        final List<Post> posts = new ArrayList<>();

        if (query.getCategory() != null) {
            Category category = categoryRepository.queryById(query.getCategory());
            if (category != null) {
                posts.addAll(postRepository.queryPosts(category, query.getStartNo(), query.getCount()));
            }
        } else if (query.getSection() != null) {
            Section section = sectionRepository.queryById(query.getSection());
            if (section != null) {
                posts.addAll(postRepository.queryPosts(section, query.getStartNo(), query.getCount()));
            }
        }

        return posts.stream().map(postConverter::to).collect(Collectors.toList());
    }
}

