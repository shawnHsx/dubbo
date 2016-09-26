package com.jd.nio;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by heshuanxu on 2016/9/18.
 */
public class MyResponseObject implements Serializable {

    private static final long serialVersionUID = 4696326090024768991L;

    private String name;

    private String value;

    private byte[] bytes;

    public MyResponseObject(String name, String value) {
        this.name = name;
        this.value = value;
        this.bytes = new byte[1024];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "MyResponseObject{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", bytes=" + bytes.length +
                '}';
    }
}
