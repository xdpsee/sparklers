package com.zhenhui.sparkler.data.model.core.common.misc.content;

import com.zhenhui.sparkler.data.model.core.common.misc.Mixed;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


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
