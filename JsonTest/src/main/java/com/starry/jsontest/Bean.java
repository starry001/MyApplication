package com.starry.jsontest;

/**
 * @author Scrige
 * @desc
 * @time 2016/7/28 0028 13:05
 */
public class Bean {

    boolean result;
    int location;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "result=" + result +
                ", location=" + location +
                '}';
    }
}
