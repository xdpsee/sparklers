package com.zhenhui.sparkler.data.model.core.post;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("Section")
public class Section implements Serializable {

    private static final long serialVersionUID = 4427380234384709872L;

    private Long id;

    private Long categoryId;

    private String title;

    private String description;

    private Integer status;
}

