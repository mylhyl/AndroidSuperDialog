package com.mylhyl.superdialog.sample;

/**
 * Created by hupei on 2016/3/15 20:29.
 */
public class People {
    public int id;
    public String name;

    public People() {
    }

    public People(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
