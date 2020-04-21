package com.herion.everyknow.seims.deploy;

import com.herion.everyknow.seims.config.EknowConfig;
import com.herion.everyknow.seims.utils.AESUtil;
import com.herion.everyknow.seims.utils.AESUtil2;
import com.herion.everyknow.seims.utils.AesCipherUtil;
import com.herion.everyknow.seims.utils.Base64ConvertUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

import static com.herion.everyknow.seims.utils.AESUtil2.aesDecrypt;
import static com.herion.everyknow.seims.utils.AESUtil2.aesEncrypt;

/**
 * @Description 加密解密测试
 * @auther wubo25320
 * @create 2020-04-20 18:43
 */
@SpringBootTest
public class AesCipherTest {

    @Test
    public void enCodeTest() {
        try {
            System.out.println(Base64ConvertUtil.decode(EknowConfig.getEncryptAESKey()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String password = "12345";
        String enPassword = AESUtil.encrypt(password);
        System.out.println("加密后密码: " + enPassword);

        System.out.println("解密后密码: " + AESUtil.decrypt(enPassword));
    }

    @Test
    public void enCode2Test() throws Exception {

        String AES_KEY = "cBssbHB3ZA==HKXT";
        String content = "456";
        System.out.println("加密前：" + content);
        String encrypt = aesEncrypt(content);
        System.out.println("加密后：" + encrypt);
        String decrypt = aesDecrypt(encrypt);
        System.out.println("解密后：" + decrypt);

    }

}
