package com.zhenhui.sparkler.data.model.core.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhenhui.sparkler.data.model.core.common.misc.Mixed;
import com.zhenhui.sparkler.data.model.core.common.html.Html;
import com.zhenhui.sparkler.data.model.core.common.sutra.Sutra;
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


