package com.zhenhui.sparkler.data.model.core.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("PostStats")
public class PostStats implements Serializable {

    private static final long serialVersionUID = 1124156134608645445L;

    private Long category;

    private Long section;

    private Long number;

    private Long postId;

}


