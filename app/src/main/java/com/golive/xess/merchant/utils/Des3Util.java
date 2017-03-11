package com.golive.xess.merchant.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by YangChun .
 * on 2017/3/11.
 */

public class Des3Util {
    private final static String encoding = "utf-8";                         // 编码方式
    private String secretKey = "golive_lottery@123456#$%~";                 // 密钥
    private String iv = "12345678";                                         // 向量

    private static Des3Util instance;

    private Des3Util(String secretKey, String iv) {
        if (secretKey != null && !secretKey.equals("") && iv != null && !iv.equals("")) {
            this.secretKey = secretKey;
            this.iv = iv;
        }
    }

    public static Des3Util getInstance(String secretKey, String iv) {
        synchronized (Des3Util.class) {
            if (instance == null) {
                /*String secretKey = PlatformProperties.getProperty("lomis.des3.secretKey");	// 密钥
				String iv = PlatformProperties.getProperty("lomis.des3.iv");				// 向量
*/
                instance = new Des3Util(secretKey, iv);
            }
        }
        return instance;
    }

    /**
     * 3DES加密
     *
     * @param plainText 普�?文本
     * @return
     * @throws Exception
     */
    public String encode(String plainText) throws Exception {
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        Key deskey = keyfactory.generateSecret(spec);

        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return Encodes.encodeBase64(encryptData);
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public String decode(String encryptText) throws Exception {
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        Key deskey = keyfactory.generateSecret(spec);

        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] decryptData = cipher.doFinal(Encodes.decodeBase64(encryptText));
        return new String(decryptData, encoding);
    }
}
