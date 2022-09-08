package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbJobDao;
import com.job.app.entity.TbJob;
import com.job.app.service.TbJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (TbJob)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbJobService")
public class TbJobServiceImpl extends ServiceImpl<TbJobDao, TbJob> implements TbJobService {

    @Autowired
    private TbJobDao tbJobDao;

    @Override
    public List<TbJob> listPage(int page, int size, String keyword) {
        return tbJobDao.listPage((page - 1) * size, size, keyword);
    }

    @Override
    public int count(String keyword) {
        return tbJobDao.count(keyword);
    }

    @Override
    public boolean save(TbJob tbJob) {
        return tbJobDao.save(tbJob) > 0;
    }

    @Override
    public boolean update(TbJob tbJob) {
        return tbJobDao.update(tbJob) > 0;
    }
}

