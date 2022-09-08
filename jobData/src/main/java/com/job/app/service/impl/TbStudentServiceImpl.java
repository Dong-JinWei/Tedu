package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbStudentDao;
import com.job.app.entity.TbStudent;
import com.job.app.service.TbStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (TbStudent)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbStudentService")
public class TbStudentServiceImpl extends ServiceImpl<TbStudentDao, TbStudent> implements TbStudentService {

    @Autowired
    private TbStudentDao tbStudentDao;

    @Override
    public List<TbStudent> listPage(int page, int size, String sName) {
        return tbStudentDao.listPage((page - 1) * size, size, sName);
    }

    @Override
    public int count(String sName) {
        return tbStudentDao.count(sName);
    }

    @Override
    public Boolean update(TbStudent tbStudent) {
        return tbStudentDao.update(tbStudent) > 0;
    }

    @Override
    public boolean save(TbStudent tbStudent) {
        return tbStudentDao.save(tbStudent) > 0;
    }

    @Override
    public boolean updateImagesInt(TbStudent tbStudent) {
        return tbStudentDao.updateImagesInt(tbStudent) > 0;
    }
}

