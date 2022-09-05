package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbCompanyDao;
import com.job.app.entity.TbCompany;
import com.job.app.service.TbCompanyService;
import org.springframework.stereotype.Service;

/**
 * (TbCompany)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbCompanyService")
public class TbCompanyServiceImpl extends ServiceImpl<TbCompanyDao, TbCompany> implements TbCompanyService {

}

