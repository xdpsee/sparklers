package com.zhenhui.sparkler.api.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class SectionDto implements Serializable {

    private static final long serialVersionUID = -8900519624816017733L;

    private Long id;

    private Long categoryId;

    private String title;

    private String description;

    private Integer status;

}
