package com.job.app.controller;

import com.job.app.entity.TbStudent;
import com.job.app.service.TbStudentService;
import com.job.app.tools.CommonResult;
import com.job.app.tools.FtpUtil;
import com.job.app.tools.IDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * (TbStudent)表控制层
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@RestController
@RequestMapping("tbStudent")
@Api(tags = "学生控制器")
@CrossOrigin
public class TbStudentController {
    /**
     * 服务对象
     */
    @Resource
    private TbStudentService tbStudentService;

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


    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult list() {
        return CommonResult.success(tbStudentService.list());
    }

    @ApiOperation("分页查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的行数", example = "10", required = true),
            @ApiImplicitParam(name = "sName", value = "根据行业名查询")}
    )
    @GetMapping("/listPage")
    public CommonResult listPage(int page, int size, String sName) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", tbStudentService.count(sName));
        map.put("rows", tbStudentService.listPage(page, size, sName));
        return CommonResult.success(map);
    }

    @PostMapping("/save")
    @ApiOperation("插入数据")
    public CommonResult save(@RequestBody TbStudent tbStudent) {
        tbStudent.setSPhoto(url);
        return CommonResult.success(tbStudentService.save(tbStudent));
    }

    @PostMapping("/update")
    @ApiOperation("修改数据")
    public CommonResult update(@RequestBody TbStudent tbStudent) {
        return CommonResult.success(tbStudentService.update(tbStudent));
    }

    @PostMapping("/remove")
    @ApiOperation("删除数据")
    public CommonResult remove(Integer sId) {
        return CommonResult.success(tbStudentService.removeById(sId));
    }


    @PostMapping("/getImagesUrl")
    public CommonResult getImagesUrl(@RequestParam("file") MultipartFile file) {

        try { // 1、给上传的图片生成新的文件名
            // 1.1获取原始文件名
            String oldName = file.getOriginalFilename();

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
            } else {
                return CommonResult.failed("上传失败");
            }


        } catch (IOException e) {
            e.printStackTrace();
            return CommonResult.failed("出现异常，上传失败");
        }

        return CommonResult.success(url);

    }


    @PostMapping("/uploadImages")
    public CommonResult uploadImages(Integer sid, @RequestParam("file") MultipartFile file) {

        TbStudent tbStudent = tbStudentService.getById(sid);
        try { // 1、给上传的图片生成新的文件名
            // 1.1获取原始文件名
            String oldName = file.getOriginalFilename();

            // 1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));

            // 2、把图片上传到图片服务器
            // 2.1获取上传的io流
            InputStream input = file.getInputStream();

            boolean result = FtpUtil.uploadFile(host, port, userName, passWord, basePath, newName, input);

            if (result) {
                // 返回给前端图片访问路径
                String url = baseUrl + "/" + newName;
                tbStudent.setSPhoto(url);
            } else {
                return CommonResult.failed("上传失败");
            }


        } catch (IOException e) {
            e.printStackTrace();
            return CommonResult.failed("出现异常，上传失败");
        }

        return CommonResult.success(tbStudentService.updateImagesInt(tbStudent), "上传成功");

    }

    //    获取IP、端口号
    private URI getHost(URI uri) {
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
        }
        return effectiveURI;
    }


}

