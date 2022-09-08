package com.job.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.job.app.entity.TbDept;
import com.job.app.entity.TbDirection;
import com.job.app.entity.TbSpecialty;
import com.job.app.service.TbSpecialtyService;
import com.job.app.tools.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * (TbSpecialty)表控制层
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@RestController
@RequestMapping("tbSpecialty")
@Api(tags = "专业控制器")
@CrossOrigin
public class TbSpecialtyController {
    /**
     * 服务对象
     */
    @Resource
    private TbSpecialtyService tbSpecialtyService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult list(){
        return CommonResult.success(tbSpecialtyService.list());
    }


    @ApiOperation("分页所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的行数", example = "10", required = true),
            @ApiImplicitParam(name = "spName", value = "根据专业名查询")}
    )
    @GetMapping("/listPage")
    public CommonResult listPage(int page, int size, String spName) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", tbSpecialtyService.count(spName));
        map.put("rows", tbSpecialtyService.listPage(page, size, spName));
        return CommonResult.success(map);
    }

    @PostMapping("/save")
    @ApiOperation("插入数据")
    public CommonResult save(@RequestBody TbSpecialty tbSpecialty){
        return CommonResult.success(tbSpecialtyService.save(tbSpecialty));
    }

    @PostMapping("/update")
    @ApiOperation("修改数据")
    public CommonResult update(@RequestBody TbSpecialty tbSpecialty){
        return CommonResult.success(tbSpecialtyService.update(tbSpecialty));
    }

    @PostMapping("/remove")
    @ApiOperation("删除数据")
    public CommonResult remove(Integer spId){
        return CommonResult.success(tbSpecialtyService.removeById(spId));
    }


    @GetMapping("/getSpecialtyNames")
    @ApiOperation("更具院系查询专业")
    public CommonResult getSpecialtyNames(Integer deId){
        return CommonResult.success(tbSpecialtyService.getSpecialtyNames(deId));
    }


}

