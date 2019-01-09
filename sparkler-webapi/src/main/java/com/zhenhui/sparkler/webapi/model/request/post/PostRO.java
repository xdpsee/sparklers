package com.zhenhui.sparkler.webapi.model.request.post;

import com.zhenhui.sparkler.data.model.core.post.Content;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PostRO {

    @NotNull @Min(1)
    private int type;
    @NotNull
    private String title;

    private String description;
    @NotNull @Valid
    private Content content;
    @NotNull
    private Long creatorId;
    @NotNull @Min(1)
    private long sectionId;

}






