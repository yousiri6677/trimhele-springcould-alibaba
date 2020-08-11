package com.trimhelp.starter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 序列化工具类
 * @author hrimhelp
 * 2019-5-14
 */
public class ObjectTranscoderUtil {

    private static Logger logger = LoggerFactory.getLogger(ObjectTranscoderUtil.class) ;

    /**
     * 序列化
     *
     * @param value  参数
     * @return byte[]
     */
    public static byte[] serialize(Object value) {
        logger.info("序列化方法开始");
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException("Non-serializable object", e);
        } finally {
            try {
                if (os != null) {os.close();}
                if (bos != null) {bos.close();}
            } catch (Exception e2) {
                logger.error("序列化方法出现错误：{}", e2.getMessage());
            }
        }
        logger.info("序列化方法结束");
        return rv;
    }

    /**
     * 反序列化
     *
     * @param in  参数
     * @return Object
     */
    public static Object deserialize(byte[] in) {
        logger.info("反序列化方法开始");
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                rv = is.readObject();
                is.close();
                bis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {is.close();}
                if (bis != null) {bis.close();}
            } catch (Exception e2) {
                logger.error("反序列化方法出现错误：{}", e2.getMessage());
            }
        }
        logger.info("反序列化方法结束");
        return rv;
    }
}
