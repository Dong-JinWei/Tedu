package com.job.app.controller;

import com.job.app.entity.TbJob;
import com.job.app.entity.TbProtocol;
import com.job.app.service.TbJobService;
import com.job.app.service.TbProtocolService;
import com.job.app.tools.CommonResult;
import com.job.app.tools.FtpUtil;
import com.job.app.tools.IDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * (TbJob)表控制层
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@RestController
@RequestMapping("tbJob")
@Api(tags = "就业控制器")
@CrossOrigin
public class TbJobController {
    /**
     * 服务对象
     */
    @Resource
    private TbJobService tbJobService;

    @Resource
    private TbProtocolService tbProtocolService;


    @Value("${FTP.ADDRESS}")
    private String host;
    // 端口
    @Value("${FTP.PORT}")
    private int port;
    // ftp用户名
    @Value("${FTP.USERNAME}")
    private String userName;
    // ftp用户密码
    @Value("${FTP.PASSWORD}")
    private String passWord;
    // 文件在服务器端保存的主目录
    @Value("${FTP.BASEPATH}")
    private String basePath;
    // 访问图片时的基础url
    @Value("${IMAGE.BASE.URL}")
    private String baseUrl;


    private String url;
    private TbProtocol loadTbProtocol;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult list() {
        return CommonResult.success(tbJobService.list());
    }


    @ApiOperation("分页查询所有数据")
    @GetMapping("/listPage")
    public CommonResult listPage(int page, int size, String keyword) {
        Map<String, Object> map = new HashMap<>();
        map.put("total", tbJobService.count(keyword));
        map.put("rows", tbJobService.listPage(page, size, keyword));
        return CommonResult.success(map);
    }

    @PostMapping("/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile file) {

        TbProtocol tbProtocol = new TbProtocol();
        try { // 1、给上传的图片生成新的文件名
            // 1.1获取原始文件名
            String oldName = file.getOriginalFilename();
            long size = file.getSize();

            // 1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));


            // 2、把图片上传到图片服务器
            // 2.1获取上传的io流
            InputStream input = file.getInputStream();

            boolean result = FtpUtil.uploadFile(host, port, userName, passWord, basePath, newName, input);

            if (result) {
                // 返回给前端图片访问路径
                url = baseUrl + "/" + newName;
                tbProtocol.setProName(newName);
                tbProtocol.setProSize((int) size);
                tbProtocol.setProUrl(url);
                tbProtocolService.save(tbProtocol);

            } else {
                return CommonResult.failed("上传失败");
            }


        } catch (IOException e) {
            e.printStackTrace();
            return CommonResult.failed("出现异常，上传失败");
        }
        loadTbProtocol = tbProtocol;
        Integer proId = tbProtocol.getProId();
        tbProtocol.setProId(proId);
        return CommonResult.success(tbProtocol);
    }

    @PostMapping("/save")
    @ApiOperation("插入数据")
    public CommonResult save(@RequestBody TbJob tbJob) {
        tbJob.setTbProtocol(loadTbProtocol);
        return CommonResult.success(tbJobService.save(tbJob));
    }

    @PostMapping("/update")
    @ApiOperation("修改数据")
    public CommonResult update(@RequestBody TbJob tbJob) {
        tbJob.setTbProtocol(loadTbProtocol);
        return CommonResult.success(tbJobService.update(tbJob));
    }

    @PostMapping("/remove")
    @ApiOperation("删除数据")
    public CommonResult remove(Integer jId) {
        return CommonResult.success(tbJobService.removeById(jId));
    }


}

