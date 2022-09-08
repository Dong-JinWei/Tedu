package com.job.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.job.app.entity.TbJob;

import java.util.List;

/**
 * (TbJob)表服务接口
 *
 * @author DongJinwei
 * @since 2022-09-03 14:21:54
 */
public interface TbJobService extends IService<TbJob> {

    List<TbJob> listPage(int page, int size, String keyword);

    int count(String keyword);

    boolean save(TbJob tbJob);

    boolean update(TbJob tbJob);

}

