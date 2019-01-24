package com.zhenhui.sparkler.api.domain.common.misc.content;

import com.zhenhui.sparkler.api.domain.common.misc.Mixed;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

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
