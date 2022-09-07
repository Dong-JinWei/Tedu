package com.job.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.job.app.entity.TbDept;

import java.util.List;

/**
 * (TbDept)表服务接口
 *
 * @author DongJinwei
 * @since 2022-09-03 14:21:54
 */
public interface TbDeptService extends IService<TbDept> {

    int count(String deName);

    List<TbDept> listPage(int page, int size, String deName);


    boolean save(TbDept tbDept);

    boolean update(TbDept tbDept);
}

