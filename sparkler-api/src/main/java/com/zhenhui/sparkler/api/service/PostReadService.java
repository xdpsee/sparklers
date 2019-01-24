package com.zhenhui.sparkler.api.service;

import com.zhenhui.sparkler.api.domain.PostDto;
import com.zhenhui.sparkler.api.domain.PostQueryDto;

import java.util.List;

public interface PostReadService {

    PostDto getPost(long postId);

    List<PostDto> listPost(PostQueryDto query);

}
