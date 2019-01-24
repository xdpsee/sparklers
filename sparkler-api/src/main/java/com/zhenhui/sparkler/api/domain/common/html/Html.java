package com.zhenhui.sparkler.api.domain.common.html;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class Html implements Serializable {

    private static final long serialVersionUID = 3582111049019020481L;

    @NotEmpty
    private String text;
}


