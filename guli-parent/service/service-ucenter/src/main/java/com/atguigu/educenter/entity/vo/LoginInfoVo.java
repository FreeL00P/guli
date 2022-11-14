package com.atguigu.educenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * LoginVo
 *
 * @author fj
 * @date 2022/11/8 15:28
 */
@Data
@ApiModel(value="登录对象", description="登录对象")
public class LoginInfoVo {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

}