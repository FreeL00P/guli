package com.atguigu.servicebase.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GuliException
 * 自定义异常
 * @author fj
 * @date 2022/10/30 17:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{
    private Integer code;//状态码
    private String msg;//异常信息


}
