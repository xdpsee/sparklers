package com.zhenhui.sparkler.data.model.core.comment;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Alias("CommentRelation")
public class CommentRelation {
    private Long Id;
    @NonNull
    private Long refId;
    @NonNull
    private Long commentId;
    @NonNull
    private Integer number;
}


