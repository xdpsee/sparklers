package com.zhenhui.sparkler.data.model.core.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhenhui.sparkler.api.domain.common.Content;
import com.zhenhui.sparkler.data.model.validator.ValidPost;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@ValidPost
@JsonInclude(JsonInclude.Include.NON_NULL)
@Alias("Post")
public class Post implements Serializable {

    private static final long serialVersionUID = -6383375006530048325L;

    private Long id;
    @NotNull
    private Type type;
    @NotNull
    private String title;

    private String description;
    @NotNull @Valid
    private Content content;
    @NotNull
    private Date createAt;
    @NotNull
    private Date updateAt;
    @NotNull
    private Long creatorId;
    @NotNull
    private Integer status = 0;
}

