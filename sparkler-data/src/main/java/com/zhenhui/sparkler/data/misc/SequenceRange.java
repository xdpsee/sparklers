package com.zhenhui.sparkler.data.misc;

import java.util.concurrent.atomic.AtomicLong;

public class SequenceRange {
    private final long max;
    private final AtomicLong value;
    private volatile boolean over = false;

    public SequenceRange(long min, long max) {
        this.max = max;
        this.value = new AtomicLong(min);
    }

    public long getAndIncrement() {
        long curr = value.getAndIncrement();
        if (curr > max) {
            over = true;
            return -1;
        }

        return curr;
    }

    public boolean isOver() {
        return this.over;
    }
}
