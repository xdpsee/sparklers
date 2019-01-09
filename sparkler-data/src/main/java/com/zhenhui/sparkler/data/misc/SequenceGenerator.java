package com.zhenhui.sparkler.data.misc;

public interface SequenceGenerator {

    long next(char identifier) throws Exception;

}
