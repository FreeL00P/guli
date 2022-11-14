package com.atguigu.msmservice.service.impl;

import com.atguigu.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.*;
import com.aliyuncs.dysmsapi.model.v20170525.*;
/**
 * MsmServiceImpl
 *
 * @author fj
 * @date 2022/11/7 19:31
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Override
    public boolean sendMsg(String phone,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAI5tGZaG5sHga4nm29EKPE", "ieYXv7XvUWIlSOxiUT6Esh9RlYqbYl");
        /** use STS Token
         DefaultProfile profile = DefaultProfile.getProfile(
         "<your-region-id>",           // The region ID
         "<your-access-key-id>",       // The AccessKey ID of the RAM account
         "<your-access-key-secret>",   // The AccessKey Secret of the RAM account
         "<your-sts-token>");          // STS Token
         **/

        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("阿里云短信测试");
        request.setTemplateCode("SMS_154950909");
        request.setPhoneNumbers(phone);
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            return true;
        } catch (ServerException e) {
            e.printStackTrace();
            return false;
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
            return false;
        }


    }
}
