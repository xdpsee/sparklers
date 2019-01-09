package com.zhenhui.sparkler.data.model.core.reply;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Alias("ReplyStats")
public class ReplyStats {

    @NonNull
    private Long postId;

    private int count = 0;

    private int version = 0;

}
