package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbAreaDao;
import com.job.app.entity.TbArea;
import com.job.app.service.TbAreaService;
import org.springframework.stereotype.Service;

/**
 * (TbArea)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:06
 */
@Service("tbAreaService")
public class TbAreaServiceImpl extends ServiceImpl<TbAreaDao, TbArea> implements TbAreaService {

}

