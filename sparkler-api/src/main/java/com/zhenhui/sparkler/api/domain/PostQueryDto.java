package com.zhenhui.sparkler.api.domain;

import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@SuppressWarnings("unused")
public class PostQueryDto {

    @Getter
    @Nullable
    private Long category;
    @Getter
    @NonNull
    private Long section;
    @Getter
    @NonNull
    private Long startNo = 0L;
    @Getter
    @NonNull
    private Integer count = 15;

    public static PostQueryDto forCategory(long categoryId, Long startNo, int count) {
        PostQueryDto query = new PostQueryDto();
        query.category = categoryId;
        query.startNo = startNo;
        query.count = count;

        return query;
    }

    public static PostQueryDto forSection(long sectionId, Long startNo, int count) {
        PostQueryDto query = new PostQueryDto();
        query.section = sectionId;
        query.startNo = startNo;
        query.count = count;

        return query;
    }
}



