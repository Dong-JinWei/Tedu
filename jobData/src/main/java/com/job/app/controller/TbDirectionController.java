package com.job.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.job.app.entity.TbArea;
import com.job.app.entity.TbDirection;
import com.job.app.service.TbDirectionService;
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
 * (TbDirection)表控制层
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@RestController
@RequestMapping("tbDirection")
@Api(tags = "就业方向控制器")
@CrossOrigin
public class TbDirectionController {
    /**
     * 服务对象
     */
    @Resource
    private TbDirectionService tbDirectionService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult list(){
        return CommonResult.success(tbDirectionService.list());
    }

    @ApiOperation("分页查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的行数", example = "10", required = true),
            @ApiImplicitParam(name = "diName", value = "根据行业名查询")}
    )
    @GetMapping("/listPage")
    public CommonResult listPage(int page, int size, String diName) {
        QueryWrapper<TbDirection> queryWrapper = new QueryWrapper<>();
        if (!"".equals(diName) && diName != null) {
            queryWrapper.like("di_name", diName);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", tbDirectionService.count(queryWrapper));
        map.put("rows", tbDirectionService.page(new Page<TbDirection>(page, size), queryWrapper).getRecords());
        return CommonResult.success(map);
    }
}

