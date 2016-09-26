package com.semion.nio;

import java.io.Serializable;

/**
 * Created by heshuanxu on 2016/9/18.
 */
public class MyRequestObject implements Serializable {

    private static final long serialVersionUID = -3766970295910259882L;

    private String name;

    private String value;

    private byte[] bytes;


    public MyRequestObject(String name, String value) {
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
        return "MyRequestObject{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", bytes=" + bytes.length +
                '}';
    }
}
