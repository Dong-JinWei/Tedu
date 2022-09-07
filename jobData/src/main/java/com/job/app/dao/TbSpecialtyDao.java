package com.job.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.job.app.entity.TbSpecialty;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TbSpecialty)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-09-03 14:26:05
 */
@Repository
public interface TbSpecialtyDao extends BaseMapper<TbSpecialty> {

    List<TbSpecialty> listPage(int page, int size, String spName);

    int count(String spName);

    boolean save(TbSpecialty tbSpecialty);

    Boolean update(TbSpecialty tbSpecialty);
}

