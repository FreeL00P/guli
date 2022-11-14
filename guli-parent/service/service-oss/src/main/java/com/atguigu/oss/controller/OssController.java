package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * OssController
 *
 * @author fj
 * @date 2022/10/31 14:23
 */
@CrossOrigin
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        //获取上传文件
        //返回上传文件的oss的路径
        String url = ossService.uploadFile(file);
        return R.ok().message(url);
    }
}
