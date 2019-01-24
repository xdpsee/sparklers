package com.zhenhui.sparkler.api.domain.common.misc.content;

import com.zhenhui.sparkler.api.domain.common.misc.Mixed;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class Audio extends Mixed.Content {

    public Audio() {
        super(3);
    }

    @NotEmpty
    private String url;

    private Integer duration = 0;

}
