package com.lpcoder.agile.base.forj.util;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import lombok.SneakyThrows;

/**
 * Created by lierlin on 16/10/12.
 */
public class AESUtil {

    private static final String DEFAULT_SECURITY_KEY = "%6vr&R^%V%^";

    private static String SECURITY_KEY = DEFAULT_SECURITY_KEY;

    private static Cipher encodeCipher = null;
    private static Cipher decodeCipher = null;

    public static void setSecurityKey(String securityKey) {
        if (StringUtil.isBlank(securityKey)) {
            throw new RuntimeException("securityKey must not be blank");
        }
        SECURITY_KEY = securityKey;

        setupConfig();
    }

    static {
        setSecurityKey(SECURITY_KEY);
    }

    @SneakyThrows
    private static void setupConfig() {
        //1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //2.根据ecnodeRules规则初始化密钥生成器
        //生成一个128位的随机源,根据传入的字节数组
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(SECURITY_KEY.getBytes());
        keygen.init(128, random);
        //3.产生原始对称密钥
        SecretKey original_key = keygen.generateKey();
        //4.获得原始对称密钥的字节数组
        byte[] raw = original_key.getEncoded();
        //5.根据字节数组生成AES密钥
        SecretKey key = new SecretKeySpec(raw, "AES");

        //6.根据指定算法AES自成密码器
        encodeCipher = Cipher.getInstance("AES");
        encodeCipher.init(Cipher.ENCRYPT_MODE, key);

        decodeCipher = Cipher.getInstance("AES");
        decodeCipher.init(Cipher.DECRYPT_MODE, key);
    }

    /*
     * 加密
     * 1.构造密钥生成器
     * 2.根据SECURITY_KEY规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
    public static String encode(String content) {
        try {
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byte_encode = content.getBytes(StandardCharsets.UTF_8);
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = encodeCipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            //11.将字符串返回
            return Base64.encodeBase64URLSafeString(byte_AES);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String decode(String content) {
        try {
            //8.将加密并编码后的内容解码成字节数组
            byte[] byte_content = Base64.decodeBase64(content);
            /*
             * 解密
             */
            byte[] byte_decode = decodeCipher.doFinal(byte_content);
            return new String(byte_decode, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
