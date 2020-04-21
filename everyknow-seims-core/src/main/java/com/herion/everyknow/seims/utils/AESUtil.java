package com.herion.everyknow.seims.utils;

import com.herion.everyknow.common.exception.EKnowException;
import com.herion.everyknow.seims.config.EknowConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Description AES-加密解密工具
 * @auther wubo25320
 * @create 2020-04-20 20:07
 */
@Slf4j
// 暂时废弃
public class AESUtil {

    // 使用AES-128-CBC 加密模式, key 和 IV 都需要最少 16位. KEY 和 IV 可以相同
    private static String KEY = EknowConfig.getEncryptAESKey();

    private static String IV = EknowConfig.getEncryptAESKey();

    /**
     * 加密
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding"); // 算法/模式/补码方式
            int blockSize = cipher.getBlockSize();

            byte[] strBytes = str.getBytes();
            int plaintextLength = strBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(strBytes, 0, plaintext, 0, strBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(Base64ConvertUtil.decode(KEY).getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(Base64ConvertUtil.decode(IV).getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return Base64ConvertUtil.encode(encrypted.toString());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            log.error("getInstance()方法异常:{}", e.getMessage());
            throw new EKnowException("getInstance()方法异常:" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            log.error("Base64加密异常:{}", e.getMessage());
            throw new EKnowException("Base64加密异常:" + e.getMessage());
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            log.error("初始化Cipher对象异常:{}", e.getMessage());
            throw new EKnowException("初始化Cipher对象异常:" + e.getMessage());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            log.error("加密异常，密钥有误:{}", e.getMessage());
            throw new EKnowException("加密异常，密钥有误:" + e.getMessage());
        }
    }

    public static String decrypt(String str) {
        try {
            byte[] strBytes = Base64ConvertUtil.decode(str).getBytes();

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding"); // 算法/模式/补码方式
            int blockSize = cipher.getBlockSize();

            int plaintextLength = strBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(strBytes, 0, plaintext, 0, strBytes.length);

            SecretKeySpec keySpec = new SecretKeySpec(Base64ConvertUtil.decode(KEY).getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(Base64ConvertUtil.decode(IV).getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivspec);
            byte[] original = cipher.doFinal(plaintext);
            return new String(original, Charsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            log.error("getInstance()方法异常:{}", e.getMessage());
            throw new EKnowException("getInstance()方法异常:" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            log.error("Base64解密异常:{}", e.getMessage());
            throw new EKnowException("Base64解密异常:" + e.getMessage());
        } catch (InvalidKeyException | InvalidAlgorithmParameterException  e) {
            log.error("初始化Cipher对象异常:{}", e.getMessage());
            throw new EKnowException("初始化Cipher对象异常:" + e.getMessage());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            log.error("解密异常，密钥有误:{}", e.getMessage());
            throw new EKnowException("解密异常，密钥有误:" + e.getMessage());
        }
    }
}
