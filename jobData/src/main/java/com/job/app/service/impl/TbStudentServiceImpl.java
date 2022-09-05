package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbStudentDao;
import com.job.app.entity.TbStudent;
import com.job.app.service.TbStudentService;
import org.springframework.stereotype.Service;

/**
 * (TbStudent)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbStudentService")
public class TbStudentServiceImpl extends ServiceImpl<TbStudentDao, TbStudent> implements TbStudentService {

}

