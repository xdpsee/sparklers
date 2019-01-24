package com.zhenhui.sparkler.api.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhenhui.sparkler.api.domain.common.html.Html;
import com.zhenhui.sparkler.api.domain.common.misc.Mixed;
import com.zhenhui.sparkler.api.domain.common.sutra.Sutra;
import lombok.Data;

import javax.validation.Valid;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Content {

    @Valid
    private Html html;
    @Valid
    private Mixed mixed;
    @Valid
    private Sutra sutra;

}


