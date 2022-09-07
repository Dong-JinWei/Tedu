package com.job.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.job.app.entity.TbDirection;
import com.job.app.entity.TbRegion;
import com.job.app.service.TbRegionService;
import com.job.app.tools.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TbRegion)表控制层
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@RestController
@RequestMapping("tbRegion")
@Api(tags = "行政控制器")
@CrossOrigin
public class TbRegionController {
    /**
     * 服务对象
     */
    @Resource
    private TbRegionService tbRegionService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult list(){
        return CommonResult.success(tbRegionService.list());
    }

    @ApiOperation("分页查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的行数", example = "10", required = true),
            @ApiImplicitParam(name = "reName", value = "根据城市名查询")}
    )
    @GetMapping("/listPage")
    public CommonResult listPage(int page, int size, String reName) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", tbRegionService.count(reName));
        map.put("rows", tbRegionService.listPage(page, size, reName));
        return CommonResult.success(map);
    }

    @PostMapping("/save")
    @ApiOperation("插入数据")
    public CommonResult save(@RequestBody TbRegion tbRegion){
        return CommonResult.success(tbRegionService.save(tbRegion));
    }

    @PostMapping("/update")
    @ApiOperation("修改数据")
    public CommonResult update(@RequestBody TbRegion tbRegion){
        return CommonResult.success(tbRegionService.update(tbRegion));
    }

    @GetMapping("/getRegion")
    @ApiOperation("级联查询")
    public CommonResult getRegion(){

        ArrayList<Map<String, Object>> maps = new ArrayList<>();

        List<String> shortnames = tbRegionService.getShortname();
        for (String shortname : shortnames) {
            HashMap<String, Object> map = new HashMap<>();
            // map.put("value", shortname);
            map.put("label", shortname);
            List<TbRegion> names = tbRegionService.getName(shortname);
            ArrayList<Map<String, Object>> childrenList = new ArrayList<>();
            for (TbRegion name : names) {
                HashMap<String, Object> children = new HashMap<>();
                children.put("value", name.getReId());
                children.put("label", name.getReName());
                childrenList.add(children);
            }
            map.put("children", childrenList);
            maps.add(map);
        }

        return CommonResult.success(maps);
    }

}

