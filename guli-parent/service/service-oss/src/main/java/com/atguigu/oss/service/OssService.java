package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * OssService
 *
 * @author fj
 * @date 2022/10/31 14:24
 */
public interface OssService {
    String uploadFile(MultipartFile file);
}
