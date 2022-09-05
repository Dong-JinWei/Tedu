package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbProtocolDao;
import com.job.app.entity.TbProtocol;
import com.job.app.service.TbProtocolService;
import org.springframework.stereotype.Service;

/**
 * (TbProtocol)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbProtocolService")
public class TbProtocolServiceImpl extends ServiceImpl<TbProtocolDao, TbProtocol> implements TbProtocolService {

}

