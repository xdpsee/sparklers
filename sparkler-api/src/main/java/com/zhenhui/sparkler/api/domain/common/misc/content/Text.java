package com.zhenhui.sparkler.api.domain.common.misc.content;

import com.zhenhui.sparkler.api.domain.common.misc.Mixed;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class Text extends Mixed.Content {

    public Text() {
        super(1);
    }

    @NotEmpty
    private String value;

    private String url;

}
