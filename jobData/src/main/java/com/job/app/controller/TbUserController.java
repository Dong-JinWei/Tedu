package com.job.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.job.app.entity.TbDirection;
import com.job.app.entity.TbUser;
import com.job.app.service.TbUserService;
import com.job.app.tools.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * (TbUser)表控制层
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@RestController
@RequestMapping("tbUser")
@Api(tags = "用户控制器")
public class TbUserController {
    /**
     * 服务对象
     */
    @Resource
    private TbUserService tbUserService;


    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult list(){
        return CommonResult.success(tbUserService.list());
    }

    @ApiOperation("分页查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的行数", example = "10", required = true),
            @ApiImplicitParam(name = "uName", value = "根据用户名查询")}
    )
    @GetMapping("/listPage")
    public CommonResult listPage(int page, int size, String uName) {
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        if (!"".equals(uName) && uName != null) {
            queryWrapper.like("u_name", uName);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", tbUserService.count(queryWrapper));
        map.put("rows", tbUserService.page(new Page<TbUser>(page, size), queryWrapper).getRecords());
        return CommonResult.success(map);
    }

}

