package com.zhenhui.sparkler.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhenhui.sparkler.api.domain.PostDto;
import com.zhenhui.sparkler.api.service.PostWriteService;
import com.zhenhui.sparkler.data.misc.SequenceGenerator;
import com.zhenhui.sparkler.data.model.core.post.Post;
import com.zhenhui.sparkler.data.model.core.post.Section;
import com.zhenhui.sparkler.data.repository.PostRepository;
import com.zhenhui.sparkler.data.repository.SectionRepository;
import com.zhenhui.sparkler.service.converter.PostConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class PostWriteServiceImpl implements PostWriteService {

    private static final Logger logger = LoggerFactory.getLogger(PostWriteServiceImpl.class);

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private SequenceGenerator sequenceGenerator;

    private static final char SEQUENCE_IDENTIFIER = 'A';

    @Override
    public long createPost(long sectionId, PostDto post) {

        final Section section = sectionRepository.queryById(sectionId);
        if (section == null) {
            throw new IllegalArgumentException("invalid section id: " + sectionId);
        }

        long number = sequenceGenerator.next(SEQUENCE_IDENTIFIER);
        Post ret = postRepository.createPost(postConverter.from(post)
                , section
                , number);

        return ret.getId();

    }

}

