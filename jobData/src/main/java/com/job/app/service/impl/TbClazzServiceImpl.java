package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbClazzDao;
import com.job.app.entity.TbClazz;
import com.job.app.service.TbClazzService;
import org.springframework.stereotype.Service;

/**
 * (TbClazz)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbClazzService")
public class TbClazzServiceImpl extends ServiceImpl<TbClazzDao, TbClazz> implements TbClazzService {

}

