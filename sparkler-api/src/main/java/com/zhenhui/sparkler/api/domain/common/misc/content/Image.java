package com.zhenhui.sparkler.api.domain.common.misc.content;

import com.zhenhui.sparkler.api.domain.common.misc.Mixed;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class Image extends Mixed.Content {

    public Image() {
        super(2);
    }

    @NotEmpty
    private String url;

    private Integer width = 0;

    private Integer height = 0;

}
