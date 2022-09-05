package com.job.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.job.app.entity.TbArea;
import com.job.app.entity.TbCollege;
import com.job.app.service.TbCollegeService;
import com.job.app.tools.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * (TbCollege)表控制层
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@RestController
@RequestMapping("tbCollege")
@Api(tags = "院校控制器")
public class TbCollegeController {
    /**
     * 服务对象
     */
    @Resource
    private TbCollegeService tbCollegeService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult list(){
        return CommonResult.success(tbCollegeService.list());
    }


    @ApiOperation("分页查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的行数", example = "10", required = true),
            @ApiImplicitParam(name = "coName", value = "根据学校名称查询")}
    )
    @GetMapping("/listPage")
    public CommonResult listPage(int page, int size, String coName) {
        QueryWrapper<TbCollege> queryWrapper = new QueryWrapper<>();
        if (!"".equals(coName) && coName != null) {
            queryWrapper.like("co_name", coName);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", tbCollegeService.count(queryWrapper));
        map.put("rows", tbCollegeService.page(new Page<TbCollege>(page, size), queryWrapper).getRecords());
        return CommonResult.success(map);
    }
}

