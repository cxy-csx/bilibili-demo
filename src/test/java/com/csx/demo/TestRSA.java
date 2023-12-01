package com.csx.demo;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@SpringBootTest
public class TestRSA {

    @Test
    public void test(){

        String text = "123456";
        System.out.println("原文：" + text);

        // 生成公私钥对
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        // 获得私钥
        String privateKeyStr = bytesToBase64(privateKey.getEncoded());
        System.out.println("私钥：" + privateKeyStr);
        // 获得公钥
        String publicKeyStr = bytesToBase64(publicKey.getEncoded());
        System.out.println("公钥：" + publicKeyStr);

        RSA rsa = new RSA(privateKeyStr, publicKeyStr);
        System.out.println(rsa);

        //公钥加密，私钥解密
//        byte[] encrypt = rsa.encrypt(StrUtil.bytes(text, CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
//        System.out.println("公钥加密：" + bytesToBase64(encrypt));
//
//        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
//        System.out.println("私钥解密：" + new String(decrypt,StandardCharsets.UTF_8));

        //私钥加密，公钥解密
//        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes(text, CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
//        System.out.println("私钥加密：" + bytesToBase64(encrypt2));
//        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);
//        System.out.println("公钥解密：" + new String(decrypt2, StandardCharsets.UTF_8));

//        Sign sign = SecureUtil.sign(SignAlgorithm.SHA256withRSA, privateKeyStr, publicKeyStr);
//        // 签名
//        byte[] data = text.getBytes();
//        byte[] signed = sign.sign(data);
//        String signedStr = bytesToBase64(signed);
//        System.out.println("签名：" + signedStr);
//
//        // 验证签名
//        boolean verify = sign.verify(data, base64ToBytes(signedStr));
//        System.out.println("验签：" + verify);

    }


    /**
     * 字节数组转Base64编码
     *
     * @param bytes 字节数组
     * @return Base64编码
     */
    private static String bytesToBase64(byte[] bytes) {
        byte[] encodedBytes = Base64.getEncoder().encode(bytes);
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Base64编码转字节数组
     *
     * @param base64Str Base64编码
     * @return 字节数组
     */
    private static byte[] base64ToBytes(String base64Str) {
        byte[] bytes = base64Str.getBytes(StandardCharsets.UTF_8);
        return Base64.getDecoder().decode(bytes);
    }
}
