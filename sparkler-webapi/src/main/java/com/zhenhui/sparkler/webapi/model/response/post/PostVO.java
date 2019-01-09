package com.zhenhui.sparkler.webapi.model.response.post;

import com.zhenhui.sparkler.data.model.core.post.Content;
import lombok.Data;

@Data
public class PostVO {

    private Long id;

    private Integer type;

    private String title;

    private String description;

    private Content content;

    private Long createAt;

    private UserVO user;

    private Integer status = 0;
}
