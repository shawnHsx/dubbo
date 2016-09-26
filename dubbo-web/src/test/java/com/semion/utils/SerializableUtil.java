package com.semion.utils;
import java.io.*;

/**
 * 对象的序列化 与反序列化
 */
public class SerializableUtil {
    /**
     * 将对象转为字节数组
     * @param object
     * @return
     */
    public static byte[] toBytes(Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch(IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            try {
                oos.close();
            } catch (Exception e) {}
        }
    }

    /**
     * 将字节数组反序列化为对象
     * @param bytes
     * @return
     */
    public static Object toObject(byte[] bytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bais);
            Object object = ois.readObject();
            return object;
        } catch(IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch(ClassNotFoundException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            try {
                ois.close();
            } catch (Exception e) {}
        }
    }
}