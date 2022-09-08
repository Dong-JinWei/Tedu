package com.job.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.job.app.entity.TbClazz;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TbClazz)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-09-03 14:26:05
 */
@Repository
public interface TbClazzDao extends BaseMapper<TbClazz> {

    int count(String clName);

    List<TbClazz> listPage(int page, int size, String clName);

    int update(TbClazz tbClazz);

    int save(TbClazz tbClazz);


    List<TbClazz> getClazzNames(Integer spId);
}

