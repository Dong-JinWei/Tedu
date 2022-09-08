package com.job.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.job.app.entity.TbArea;
import com.job.app.entity.TbClazz;
import com.job.app.entity.TbSpecialty;
import com.job.app.service.TbClazzService;
import com.job.app.tools.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * (TbClazz)表控制层
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@RestController
@RequestMapping("tbClazz")
@Api(tags = "班级控制器")
@CrossOrigin
public class TbClazzController {
    /**
     * 服务对象
     */
    @Resource
    private TbClazzService tbClazzService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult list(){
        return CommonResult.success(tbClazzService.list());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的行数", example = "10", required = true),
            @ApiImplicitParam(name = "clName", value = "根据班级名称查询")}
    )
    @ApiOperation("分页查询所有数据")
    @GetMapping("/listPage")
    public CommonResult listPage(int page, int size, String clName) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", tbClazzService.count(clName));
        map.put("rows", tbClazzService.listPage(page, size, clName));
        return CommonResult.success(map);
    }

    @PostMapping("/save")
    @ApiOperation("插入数据")
    public CommonResult save(@RequestBody TbClazz tbClazz){
        return CommonResult.success(tbClazzService.save(tbClazz));
    }

    @PostMapping("/update")
    @ApiOperation("修改数据")
    public CommonResult update(@RequestBody TbClazz tbClazz){
        return CommonResult.success(tbClazzService.update(tbClazz));
    }

    @PostMapping("/remove")
    @ApiOperation("删除数据")
    public CommonResult remove(Integer clId){
        return CommonResult.success(tbClazzService.removeById(clId));
    }


    @GetMapping("/getClazzNames")
    @ApiOperation("根据专业查班级")
    public CommonResult getClazzNames(Integer spId){
        return CommonResult.success(tbClazzService.getClazzNames(spId));
    }
}

