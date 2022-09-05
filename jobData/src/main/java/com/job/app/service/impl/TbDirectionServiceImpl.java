package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbDirectionDao;
import com.job.app.entity.TbDirection;
import com.job.app.service.TbDirectionService;
import org.springframework.stereotype.Service;

/**
 * (TbDirection)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbDirectionService")
public class TbDirectionServiceImpl extends ServiceImpl<TbDirectionDao, TbDirection> implements TbDirectionService {

}

