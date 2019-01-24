package com.zhenhui.sparkler.api.service;

import com.zhenhui.sparkler.api.domain.PostDto;

public interface PostWriteService {

    long createPost(long sectionId, PostDto post);

}
