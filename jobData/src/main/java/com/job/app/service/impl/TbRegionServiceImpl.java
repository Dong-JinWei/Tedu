package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbRegionDao;
import com.job.app.entity.TbRegion;
import com.job.app.service.TbRegionService;
import org.springframework.stereotype.Service;

/**
 * (TbRegion)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbRegionService")
public class TbRegionServiceImpl extends ServiceImpl<TbRegionDao, TbRegion> implements TbRegionService {

}

