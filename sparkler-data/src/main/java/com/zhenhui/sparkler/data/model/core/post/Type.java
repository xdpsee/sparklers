package com.zhenhui.sparkler.data.model.core.post;

import java.util.Arrays;

public enum Type {
    POST(1, ""),
    ARTICLE(2, ""),
    SUTRA(3, ""),
    ;

    public final int code;
    public final String comment;

    Type(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    public static Type valueOf(int code) {
        return Arrays.stream(values())
                .filter(t -> t.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("invalid code: %d", code)));
    }
}

