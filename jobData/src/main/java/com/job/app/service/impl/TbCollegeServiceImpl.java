package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbCollegeDao;
import com.job.app.entity.TbCollege;
import com.job.app.service.TbCollegeService;
import org.springframework.stereotype.Service;

/**
 * (TbCollege)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbCollegeService")
public class TbCollegeServiceImpl extends ServiceImpl<TbCollegeDao, TbCollege> implements TbCollegeService {

}

