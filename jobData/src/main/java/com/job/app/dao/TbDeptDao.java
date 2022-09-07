package com.job.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.job.app.entity.TbDept;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TbDept)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-09-03 14:26:05
 */
@Repository
public interface TbDeptDao extends BaseMapper<TbDept> {

    int count(String deName);

    List<TbDept> listPage(int page, int size, String deName);

    boolean save(TbDept tbDept);

    boolean update(TbDept tbDept);
}

