package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbRegionDao;
import com.job.app.entity.TbRegion;
import com.job.app.service.TbRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (TbRegion)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbRegionService")
public class TbRegionServiceImpl extends ServiceImpl<TbRegionDao, TbRegion> implements TbRegionService {


    @Autowired
    private TbRegionDao tbRegionDao;


    @Override
    public List<TbRegion> listPage(int page, int size, String reName) {
        return tbRegionDao.listPage((page - 1) * size, size, reName);
    }

    @Override
    public int count(String reName) {
        return tbRegionDao.count(reName);
    }

    @Override
    public boolean save(TbRegion tbRegion) {
        return tbRegionDao.save(tbRegion) > 0;
    }

    @Override
    public Boolean update(TbRegion tbRegion) {
        return tbRegionDao.update(tbRegion) > 0;
    }

    @Override
    public List<String> getShortname() {
        return tbRegionDao.getShortname();
    }

    @Override
    public List<TbRegion> getName(String reShortname) {
        return tbRegionDao.getName(reShortname);
    }


}

