package com.zhenhui.sparkler.data.model.core.comment;

import com.zhenhui.sparkler.data.model.core.common.misc.Mixed;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Alias("Comment")
public class Comment {
    private Long id;
    @NotNull
    private Long replyId;
    @NotNull
    private Long refId = 0L;
    @NotNull
    private Date createAt;
    @NotNull
    private Long creatorId;
    @NotNull
    private Integer number = 0;
    @NotNull
    private Mixed content;
    @NotNull
    private Integer status = 0;
}
