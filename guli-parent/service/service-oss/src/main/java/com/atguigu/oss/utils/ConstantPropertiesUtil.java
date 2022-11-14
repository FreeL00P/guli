package com.atguigu.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ConstanPropertiesUtils
 *
 * @author fj
 * @date 2022/10/31 14:20
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
    //读取配置文件内容
    //@Value("${aliyun.oss.file.endpoint}")
    private String endpoint="oss-cn-hangzhou.aliyuncs.com";
    //@Value("${aliyun.oss.file.keyid}")
    private String keyId="LTAI5tGZaG5sHga4nm29EKPE";
    //@Value("${aliyun.oss.file.keysecret}")
    private String keySecret="ieYXv7XvUWIlSOxiUT6Esh9RlYqbYl";

    //@Value("${aliyun.oss.file.bucketname}")
    private String bucketName="freel00p";
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
