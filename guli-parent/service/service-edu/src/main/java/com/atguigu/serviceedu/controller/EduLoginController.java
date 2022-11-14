package com.atguigu.serviceedu.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * EduLoginController
 *
 * @author fj
 * @date 2022/10/31 10:39
 */
@CrossOrigin//解决跨域
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    @PostMapping("login")
    public R login() {
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202105%2F04%2F20210504062111_d8dc3.thumb.1000_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669776848&t=33677df16cc10574e42c817165f40b61");
    }

    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }
}
