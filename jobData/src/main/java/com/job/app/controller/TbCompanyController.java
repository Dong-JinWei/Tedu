package com.job.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.job.app.entity.TbArea;
import com.job.app.entity.TbCompany;
import com.job.app.entity.TbRegion;
import com.job.app.service.TbCompanyService;
import com.job.app.tools.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * (TbCompany)表控制层
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@RestController
@RequestMapping("tbCompany")
@Api(tags = "企业控制器")
@CrossOrigin
public class TbCompanyController {
    /**
     * 服务对象
     */
    @Resource
    private TbCompanyService tbCompanyService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult list(){
        return CommonResult.success(tbCompanyService.list());
    }

    @ApiOperation("分页查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的行数", example = "10", required = true),
            @ApiImplicitParam(name = "cpName", value = "根据公司名称查询")}
    )
    @GetMapping("/listPage")
    public CommonResult listPage(int page, int size, String cpName) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", tbCompanyService.count(cpName));
        map.put("rows", tbCompanyService.listPage(page, size, cpName));
        return CommonResult.success(map);
    }

    @PostMapping("/save")
    @ApiOperation("插入数据")
    public CommonResult save(@RequestBody TbCompany tbCompany){
        return CommonResult.success(tbCompanyService.save(tbCompany));
    }

    @PostMapping("/update")
    @ApiOperation("修改数据")
    public CommonResult update(@RequestBody TbCompany tbCompany){
        return CommonResult.success(tbCompanyService.update(tbCompany));
    }

    @PostMapping("/remove")
    @ApiOperation("删除数据")
    public CommonResult remove(Integer cpId){
        return CommonResult.success(tbCompanyService.removeById(cpId));
    }

}

