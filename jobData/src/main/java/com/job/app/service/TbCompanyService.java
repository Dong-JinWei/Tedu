package com.job.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.job.app.entity.TbCompany;
import com.job.app.entity.TbRegion;

import java.util.List;

/**
 * (TbCompany)表服务接口
 *
 * @author DongJinwei
 * @since 2022-09-03 14:21:54
 */
public interface TbCompanyService extends IService<TbCompany> {

    List<TbCompany> listPage(int page, int size,String cpName);

    int count(String cpName);

    boolean save(TbCompany tbCompany);

    boolean update(TbCompany tbCompany);

}

