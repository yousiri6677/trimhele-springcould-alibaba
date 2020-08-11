package com.trimhelp.starter.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class AesUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    public static final String KEY = "X6AqRHJLB072OYdd1lLaJQ==";
    public static final String LW_KEY = "dIF6TiOgFid0v+aHUGWPUw==";


    /**
     * @desc: AES对称-加密操作
     * @param keyStr 进行了Base64编码的秘钥
     * @param data 需要进行加密的原文
     * @return String 数据密文，加密后的数据，进行了Base64的编码
     */
    public static String encrypt(String keyStr, String data) throws Exception {
        // 转换密钥
        Key key = new SecretKeySpec(Base64.getDecoder().decode(keyStr), KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 加密
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] result = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * @desc: AES对称-解密操作
     * @param keyStr 进行了Base64编码的秘钥
     * @param data 需要解密的数据<span style="color:red;">（数据必须是通过AES进行加密后，对加密数据Base64编码的数据）</span>
     * @return String 返回解密后的原文
     */
    public static String decrypt(String keyStr, String data) throws Exception {
        // 转换密钥
        Key key = new SecretKeySpec(Base64.getDecoder().decode(keyStr), KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 解密
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] result = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(result);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(keyGenerate());
        long time =System.currentTimeMillis();
        System.out.println(time);
        String data = encrypt(LW_KEY,"1"+time);
        System.out.println(data);
//        String decrypt = decrypt(LW_KEY, data);
//        System.out.println(decrypt);
    }

    /**
     * @desc: 生成AES的秘钥，秘钥进行了Base64编码的字符串
     * @return String 对生成的秘钥进行了Base64编码的字符串
     */
    public static String keyGenerate() throws Exception {
        // 生成密钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(new SecureRandom());
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        return Base64.getEncoder().encodeToString(keyBytes);
    }
}
