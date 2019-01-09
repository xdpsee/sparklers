package com.zhenhui.sparkler.data.misc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Alias("Sequence")
public class Sequence {

    private Long id;

    @NonNull
    private char name;

}
