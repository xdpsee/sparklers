package com.zhenhui.sparkler.webapi.controller;

import com.zhenhui.demo.sparklers.common.Error;
import com.zhenhui.demo.sparklers.common.Result;
import com.zhenhui.sparkler.data.misc.SequenceGenerator;
import com.zhenhui.sparkler.data.model.core.post.Category;
import com.zhenhui.sparkler.data.model.core.post.Post;
import com.zhenhui.sparkler.data.model.core.post.Section;
import com.zhenhui.sparkler.data.repository.CategoryRepository;
import com.zhenhui.sparkler.data.repository.PostRepository;
import com.zhenhui.sparkler.data.repository.SectionRepository;
import com.zhenhui.sparkler.data.utils.ValidationUtils;
import com.zhenhui.sparkler.webapi.converter.PostROConverter;
import com.zhenhui.sparkler.webapi.converter.PostVOConverter;
import com.zhenhui.sparkler.webapi.model.request.post.PostRO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private PostRepository repository;
    @Autowired
    private PostROConverter postROConverter;
    @Autowired
    private PostVOConverter postVOConverter;
    @Autowired
    private SequenceGenerator sequenceGenerator;

    private static final char SEQUENCE_IDENTIFIER = 'a';

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result createPost(@RequestBody PostRO post) {

        try {
            ValidationUtils.validate(post);

            final Section section = sectionRepository.queryById(post.getSectionId());
            if (section == null) {
                throw new IllegalArgumentException("invalid section id: " + post.getSectionId());
            }

            long number = sequenceGenerator.next(SEQUENCE_IDENTIFIER);

            Post ret = repository.createPost(Optional.of(post).map(postROConverter::to).get()
                    , section
                    , number);

            return Result.success(ret.getId());

        } catch (IllegalArgumentException e) {
            return Result.error(400, Error.INVALID_INPUT.name(), e.getMessage());
        } catch (Exception e) {
            return Result.error(Error.APPLICATION_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result getPost(@PathVariable("id") long id) {
        final Post post = repository.queryPost(id);
        if (post != null) {
            return Result.success(postVOConverter.to(post));
        }

        return Result.error(404, Error.DATA_NOT_FOUND);
    }

    @GetMapping(value = "/category/{category}/section/{section}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result getPosts(@PathVariable("category") long categoryId
            , @PathVariable("section") long sectionId
            , @RequestParam(value = "start", defaultValue = "4294967295") Long startNo
            , @RequestParam(value = "count", defaultValue = "20") Integer count) {

        final Category category = categoryRepository.queryById(categoryId);
        if (null == category) {
            return Result.error(400, Error.INVALID_INPUT.name(), "invalid category: " + categoryId);
        }

        final Section section = sectionRepository.queryById(sectionId);
        if (null == section) {
            return Result.error(400, Error.INVALID_INPUT.name(), "invalid section: " + sectionId);
        }

        if (section.getCategoryId() != categoryId) {
            return Result.error(400, Error.INVALID_INPUT.name(), "category,section mismatch!");
        }

        final List<Post> posts = repository.queryPosts(section, startNo, count);
        if (CollectionUtils.isNotEmpty(posts)) {
            return Result.success(postVOConverter.to(posts));
        }

        return Result.error(404, Error.DATA_NOT_FOUND);
    }

    @GetMapping(value = "/category/{category}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result getPosts(@PathVariable(value = "category") long categoryId
            , @RequestParam(value = "start", defaultValue = "4294967295") Long startNo
            , @RequestParam(value = "count", defaultValue = "20") Integer count) {

        Category category = categoryRepository.queryById(categoryId);
        if (null == category) {
            return Result.error(400, Error.INVALID_INPUT.name(), "invalid category: " + categoryId);
        }

        final List<Post> posts = repository.queryPosts(category, startNo, count);
        if (CollectionUtils.isNotEmpty(posts)) {
            return Result.success(postVOConverter.to(posts));
        }

        return Result.error(404, Error.DATA_NOT_FOUND);
    }
}

