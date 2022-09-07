package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbCompanyDao;
import com.job.app.entity.TbCompany;
import com.job.app.service.TbCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (TbCompany)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbCompanyService")
public class TbCompanyServiceImpl extends ServiceImpl<TbCompanyDao, TbCompany> implements TbCompanyService {

    @Autowired
    private TbCompanyDao tbCompanyDao;

    @Override
    public List<TbCompany> listPage(int page, int size, String cpName) {
        return tbCompanyDao.listPage((page - 1) * size, size, cpName);
    }

    @Override
    public int count(String cpName) {
        return tbCompanyDao.count(cpName);
    }

    @Override
    public boolean save(TbCompany tbCompany) {
        return tbCompanyDao.save(tbCompany);
    }

    @Override
    public boolean update(TbCompany tbCompany) {
        return tbCompanyDao.update(tbCompany);
    }
}

