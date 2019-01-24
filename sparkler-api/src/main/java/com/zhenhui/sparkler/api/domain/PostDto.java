package com.zhenhui.sparkler.api.domain;

import com.zhenhui.sparkler.api.domain.common.Content;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PostDto implements Serializable {

    private static final long serialVersionUID = -515341324815782693L;

    private Long id;

    private Integer type;

    private String title;

    private String description;

    private Content content;

    private Date createAt;

    private Date updateAt;

    private Long creatorId;

    private Integer status = 0;
}

