package com.job.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.job.app.entity.TbProtocol;
import org.springframework.stereotype.Repository;

/**
 * (TbProtocol)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-09-03 14:26:05
 */
@Repository
public interface TbProtocolDao extends BaseMapper<TbProtocol> {

    boolean save(TbProtocol tbProtocol);
}

