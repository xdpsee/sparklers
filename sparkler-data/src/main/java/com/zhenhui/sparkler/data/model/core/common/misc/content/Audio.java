package com.zhenhui.sparkler.data.model.core.common.misc.content;

import com.zhenhui.sparkler.data.model.core.common.misc.Mixed;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


@Data
public class Audio extends Mixed.Content {

    public Audio() {
        super(3);
    }

    @NotEmpty
    private String url;

    private Integer duration = 0;

}
