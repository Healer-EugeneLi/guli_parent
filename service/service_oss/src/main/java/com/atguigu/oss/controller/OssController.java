package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: OssController
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/3/25
 * @Time: 15:43
 */

@Api("阿里云上传文件oss")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("上传头像")
    @PostMapping("avatar")
    public R uploadOssFile(MultipartFile file){

        String url=ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }


}
