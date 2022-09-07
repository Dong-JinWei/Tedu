package com.job.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.job.app.entity.TbDirection;
import com.job.app.entity.TbStudent;
import com.job.app.service.TbStudentService;
import com.job.app.tools.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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


    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult list(){
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
        QueryWrapper<TbStudent> queryWrapper = new QueryWrapper<>();
        if (!"".equals(sName) && sName != null) {
            queryWrapper.like("s_name", sName);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", tbStudentService.count(queryWrapper));
        map.put("rows", tbStudentService.page(new Page<TbStudent>(page, size), queryWrapper).getRecords());
        return CommonResult.success(map);
    }

}

