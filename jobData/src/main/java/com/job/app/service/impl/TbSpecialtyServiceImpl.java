package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbSpecialtyDao;
import com.job.app.entity.TbSpecialty;
import com.job.app.service.TbSpecialtyService;
import org.springframework.stereotype.Service;

/**
 * (TbSpecialty)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbSpecialtyService")
public class TbSpecialtyServiceImpl extends ServiceImpl<TbSpecialtyDao, TbSpecialty> implements TbSpecialtyService {

}

