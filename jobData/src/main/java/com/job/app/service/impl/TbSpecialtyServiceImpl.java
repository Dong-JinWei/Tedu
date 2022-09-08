package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbSpecialtyDao;
import com.job.app.entity.TbSpecialty;
import com.job.app.service.TbSpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (TbSpecialty)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbSpecialtyService")
public class TbSpecialtyServiceImpl extends ServiceImpl<TbSpecialtyDao, TbSpecialty> implements TbSpecialtyService {


    @Autowired
    private TbSpecialtyDao tbSpecialtyDao;

    @Override
    public List<TbSpecialty> listPage(int page, int size, String spName) {
        return tbSpecialtyDao.listPage((page - 1) * size, size, spName);
    }

    @Override
    public int count(String spName) {
        return tbSpecialtyDao.count(spName);
    }

    @Override
    public boolean save(TbSpecialty tbSpecialty) {
        return tbSpecialtyDao.save(tbSpecialty);
    }

    @Override
    public Boolean update(TbSpecialty tbSpecialty) {
        return tbSpecialtyDao.update(tbSpecialty);
    }

    @Override
    public List<TbSpecialty> getSpecialtyNames(Integer deId) {
        return tbSpecialtyDao.getSpecialtyNames(deId);
    }

    @Override
    public List<TbSpecialty> getAllInfo(Integer spId) {
        return tbSpecialtyDao.getAllInfo(spId);
    }
}

