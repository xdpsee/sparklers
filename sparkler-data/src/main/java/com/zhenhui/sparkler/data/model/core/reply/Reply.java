package com.zhenhui.sparkler.data.model.core.reply;

import com.zhenhui.sparkler.data.model.core.common.misc.Mixed;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Alias("Reply")
public class Reply {

    private Long id;
    @NotNull
    private Long postId;
    @NotNull
    private Date createAt;
    @NotNull
    private Long creatorId;
    @NotNull
    private Integer number;
    @NotNull
    private Mixed content;
    @NotNull
    private Integer status = 0;
}
