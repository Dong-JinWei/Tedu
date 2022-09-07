package com.job.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.job.app.entity.TbRegion;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TbRegion)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-09-03 14:26:05
 */
@Repository
public interface TbRegionDao extends BaseMapper<TbRegion> {

    List<TbRegion> listPage(int page, int size, String reName);

    int count(String reName);

    int save(TbRegion tbRegion);

    int update(TbRegion tbRegion);

    List<String> getShortname();

    List<TbRegion> getName(String reShortname);
}

