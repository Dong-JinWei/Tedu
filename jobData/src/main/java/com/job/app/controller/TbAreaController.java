package com.job.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.job.app.entity.TbArea;
import com.job.app.service.TbAreaService;
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
import java.util.List;

/**
 * (TbArea)表控制层
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@RestController
@RequestMapping("tbArea")
@Api(tags = "区域控制器")
public class TbAreaController {
    /**
     * 服务对象
     */
    @Resource
    private TbAreaService tbAreaService;

    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public CommonResult list() {
        return CommonResult.success(tbAreaService.list());
    }

    @ApiOperation(value = "分页查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的行数", example = "10", required = true),
            @ApiImplicitParam(name = "arDesc", value = "根据区域信息")}
    )
    @GetMapping("/listPage")
    public CommonResult listPage(int page, int size, String arDesc) {
        QueryWrapper<TbArea> queryWrapper = new QueryWrapper<>();
        if (!"".equals(arDesc) && arDesc != null) {
            queryWrapper.like("ar_desc", arDesc);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", tbAreaService.count(queryWrapper));
        map.put("rows", tbAreaService.page(new Page<TbArea>(page, size), queryWrapper).getRecords());
        return CommonResult.success(map);
    }
}

