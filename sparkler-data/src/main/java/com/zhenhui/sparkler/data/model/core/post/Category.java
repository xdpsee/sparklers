package com.zhenhui.sparkler.data.model.core.post;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;


@Data
@Alias("Category")
public class Category implements Serializable {

    private static final long serialVersionUID = -8656446964793185385L;

    private Long id;

    private String title;

    private Integer status;

}

