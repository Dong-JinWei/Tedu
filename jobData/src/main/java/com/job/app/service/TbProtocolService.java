package com.job.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.job.app.entity.TbProtocol;

/**
 * (TbProtocol)表服务接口
 *
 * @author DongJinwei
 * @since 2022-09-03 14:21:54
 */
public interface TbProtocolService extends IService<TbProtocol> {


    boolean save(TbProtocol tbProtocol);
}

