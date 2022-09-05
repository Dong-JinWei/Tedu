package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbDeptDao;
import com.job.app.entity.TbDept;
import com.job.app.service.TbDeptService;
import org.springframework.stereotype.Service;

/**
 * (TbDept)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbDeptService")
public class TbDeptServiceImpl extends ServiceImpl<TbDeptDao, TbDept> implements TbDeptService {

}

