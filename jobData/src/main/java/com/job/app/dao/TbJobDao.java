package com.job.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.job.app.entity.BigData;
import com.job.app.entity.TbJob;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TbJob)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-09-03 14:26:05
 */
@Repository
public interface TbJobDao extends BaseMapper<TbJob> {

    List<TbJob> listPage(int page, int size, String keyword);
    List<TbJob> listAll();

    int count(String keyword);

    int save(TbJob tbJob);

    int update(TbJob tbJob);

    List<BigData> cityOrder();



}

