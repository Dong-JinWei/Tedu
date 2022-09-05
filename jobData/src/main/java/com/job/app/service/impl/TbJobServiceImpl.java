package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbJobDao;
import com.job.app.entity.TbJob;
import com.job.app.service.TbJobService;
import org.springframework.stereotype.Service;

/**
 * (TbJob)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbJobService")
public class TbJobServiceImpl extends ServiceImpl<TbJobDao, TbJob> implements TbJobService {

}

