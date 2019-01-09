package com.zhenhui.sparkler.data.model.core.common.misc.content;

import com.zhenhui.sparkler.data.model.core.common.misc.Mixed;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

@Data
public class Video extends Mixed.Content {

    public Video() {
        super(4);
    }

    @NotEmpty
    private String url;

    private Integer width = 0;

    private Integer height = 0;

    private Integer duration = 0;

    @Valid
    private Preview preview;

    @Data
    public static class Preview {

        @NotEmpty
        private String url;

        private Integer width = 0;

        private Integer height = 0;

    }

}
