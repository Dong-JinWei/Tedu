package com.job.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.job.app.dao.TbDeptDao;
import com.job.app.entity.TbArea;
import com.job.app.entity.TbCollege;
import com.job.app.entity.TbDept;
import com.job.app.service.TbDeptService;
import com.job.app.tools.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * (TbDept)表控制层
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@RestController
@RequestMapping("tbDept")
@Api(tags = "院系控制器")
@CrossOrigin
public class TbDeptController {
    /**
     * 服务对象
     */
    @Resource
    private TbDeptService tbDeptService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult list(){
        return CommonResult.success(tbDeptService.list());
    }


    @ApiOperation("分页查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的行数", example = "10", required = true),
            @ApiImplicitParam(name = "deName", value = "根据部门名称查询")}
    )
    @GetMapping("/listPage")
    public CommonResult listPage(int page, int size, String deName) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", tbDeptService.count(deName));
        map.put("rows", tbDeptService.listPage(page, size, deName));
        return CommonResult.success(map);
    }

    @PostMapping("/save")
    @ApiOperation("插入数据")
    public CommonResult save(@RequestBody TbDept tbDept){
        return CommonResult.success(tbDeptService.save(tbDept));
    }

    @PostMapping("/update")
    @ApiOperation("修改数据")
    public CommonResult update(@RequestBody TbDept tbDept){
        return CommonResult.success(tbDeptService.update(tbDept));
    }

    @PostMapping("/remove")
    @ApiOperation("删除数据")
    public CommonResult remove(Integer deId){
        return CommonResult.success(tbDeptService.removeById(deId));
    }




}

