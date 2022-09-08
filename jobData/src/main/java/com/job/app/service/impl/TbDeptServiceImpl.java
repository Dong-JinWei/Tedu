package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbDeptDao;
import com.job.app.entity.TbDept;
import com.job.app.service.TbDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (TbDept)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbDeptService")
public class TbDeptServiceImpl extends ServiceImpl<TbDeptDao, TbDept> implements TbDeptService {

    @Autowired
    private TbDeptDao tbDeptDao;

    @Override
    public int count(String deName) {
        return tbDeptDao.count(deName);
    }

    @Override
    public List<TbDept> listPage(int page, int size, String deName) {
        return tbDeptDao.listPage((page - 1) * size, size, deName);
    }

    @Override
    public boolean save(TbDept tbDept) {
        return tbDeptDao.save(tbDept);
    }

    @Override
    public boolean update(TbDept tbDept) {
        return tbDeptDao.update(tbDept);
    }

    @Override
    public List<TbDept> getDeptName(Integer coId) {
        return tbDeptDao.getDeptName(coId);
    }
}

