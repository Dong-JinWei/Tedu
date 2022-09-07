package com.job.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.job.app.entity.TbCompany;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TbCompany)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-09-03 14:26:05
 */
@Repository
public interface TbCompanyDao extends BaseMapper<TbCompany> {

    List<TbCompany> listPage(int page, int size, String cpName);

    int count(String cpName);

    boolean save(TbCompany tbCompany);

    boolean update(TbCompany tbCompany);
}

