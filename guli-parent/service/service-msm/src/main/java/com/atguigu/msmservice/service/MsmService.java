package com.atguigu.msmservice.service;

/**
 * MsmService
 *
 * @author fj
 * @date 2022/11/7 19:31
 */
public interface MsmService {
    boolean sendMsg(String phone,String code);
}
