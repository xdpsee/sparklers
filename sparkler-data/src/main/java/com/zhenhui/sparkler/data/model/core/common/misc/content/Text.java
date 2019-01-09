package com.zhenhui.sparkler.data.model.core.common.misc.content;

import com.zhenhui.sparkler.data.model.core.common.misc.Mixed;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


@Data
public class Text extends Mixed.Content {

    public Text() {
        super(1);
    }

    @NotEmpty
    private String value;

    private String url;

}
