package com.job.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.job.app.entity.TbDirection;
import com.job.app.entity.TbProtocol;
import com.job.app.service.TbProtocolService;
import com.job.app.tools.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * (TbProtocol)表控制层
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@RestController
@RequestMapping("tbProtocol")
@Api(tags = "协议控制器")
@CrossOrigin
public class TbProtocolController {
    /**
     * 服务对象
     */
    @Resource
    private TbProtocolService tbProtocolService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult list(){
        return CommonResult.success(tbProtocolService.list());
    }

    @ApiOperation("查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的行数", example = "10", required = true),
            @ApiImplicitParam(name = "proName", value = "根据协议名查询")}
    )
    @GetMapping("/listPage")
    public CommonResult listPage(int page, int size, String proName) {
        QueryWrapper<TbProtocol> queryWrapper = new QueryWrapper<>();
        if (!"".equals(proName) && proName != null) {
            queryWrapper.like("pro_name", proName);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", tbProtocolService.count(queryWrapper));
        map.put("rows", tbProtocolService.page(new Page<TbProtocol>(page, size), queryWrapper).getRecords());
        return CommonResult.success(map);
    }

}

