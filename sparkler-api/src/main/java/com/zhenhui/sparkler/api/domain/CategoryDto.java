package com.zhenhui.sparkler.api.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = -8236449499231177855L;

    private Long id;

    private String title;

    private Integer status;

}
