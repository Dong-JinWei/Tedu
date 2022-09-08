package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbClazzDao;
import com.job.app.entity.TbClazz;
import com.job.app.service.TbClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (TbClazz)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbClazzService")
public class TbClazzServiceImpl extends ServiceImpl<TbClazzDao, TbClazz> implements TbClazzService {

    @Autowired
    private TbClazzDao tbClazzDao;

    @Override
    public int count(String clName) {
        return tbClazzDao.count(clName);
    }

    @Override
    public List<TbClazz> listPage(int page, int size, String clName) {
        return tbClazzDao.listPage((page - 1) * size, size, clName);
    }

    @Override
    public Boolean update(TbClazz tbClazz) {
        return tbClazzDao.update(tbClazz) > 0;
    }

    @Override
    public boolean save(TbClazz tbClazz) {
        return tbClazzDao.save(tbClazz) > 0;
    }

    @Override
    public List<TbClazz> getClazzNames(Integer spId) {
        return tbClazzDao.getClazzNames(spId);
    }

}

