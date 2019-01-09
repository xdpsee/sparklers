package com.zhenhui.sparkler.data.model.core.common.html;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
public class Html implements Serializable {

    private static final long serialVersionUID = 3582111049019020481L;

    @NotEmpty
    private String text;
}


