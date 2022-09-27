package com.ethnicthv.bigproject.util;

public class WrappedBoolean {
    private boolean value;

    public WrappedBoolean(boolean value) {
        this.value = value;
    }

    public boolean get() {
        return value;
    }

    public void set(boolean value) {
        this.value = value;
    }
}
