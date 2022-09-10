package com.job.app.controller;

import com.job.app.entity.*;
import com.job.app.service.*;
import com.job.app.tools.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("bigData")
@Api(tags = "大屏数据获取")
@CrossOrigin
public class BigDataController {

    @Autowired
    private TbStudentService tbStudentService;

    @Autowired
    private TbJobService tbJobService;

    @Autowired
    private TbCollegeService tbCollegeService;

    @Autowired
    private TbCompanyService tbCompanyService;

    @Autowired
    private TbDirectionService tbDirectionService;


    @GetMapping("/listStudentInfo")
    public CommonResult listStudentInfo(){
        List<TbStudent> tbStudents = tbStudentService.listPage(1, 40, null);
        return CommonResult.success(tbStudents);
    }


    @GetMapping("/cityOrder")
    public CommonResult cityOrder(){
        List<BigData> bigData = tbJobService.cityOrder();
        return CommonResult.success(bigData);
    }



    @GetMapping("/listJobAll")
    public CommonResult listJobAll(){
        List<TbJob> tbJobs = tbJobService.listAll();
        return CommonResult.success(tbJobs);
    }

    @ApiOperation("数据总揽")
    @GetMapping("/userOverview")
    public CommonResult userOverview(){
        long studentNum = tbStudentService.count();
        long collegeNum = tbCollegeService.count();
        long companyNum = tbCompanyService.count();
        long directionNum = tbDirectionService.count();

        Map<String, Object> map = new HashMap<>();
        map.put("studentNum", studentNum);
        map.put("collegeNum", collegeNum);
        map.put("companyNum", companyNum);
        map.put("directionNum", directionNum);

        return CommonResult.success(map);
    }

    @GetMapping("/countJobNumberData")
    public CommonResult countJobNumberData() {
        long stuNum = tbStudentService.count();
        long jobNum = tbJobService.count();

        Map<String, Object> map = new HashMap<>();

        map.put("offlineNum", stuNum - jobNum);
        map.put("onlineNum", jobNum);
        map.put("totalNum", stuNum);
        return CommonResult.success(map);
    }



}
