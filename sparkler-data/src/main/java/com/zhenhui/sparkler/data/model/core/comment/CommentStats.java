package com.zhenhui.sparkler.data.model.core.comment;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.Alias;



@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Alias("CommentStats")
public class CommentStats {
    @NonNull
    private Long replyId;
    @NonNull
    private Long refId;

    private int count = 0;

    private Long version = 0L;
}

