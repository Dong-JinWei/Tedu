package com.job.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.job.app.entity.TbRegion;
import com.job.app.entity.TbSpecialty;

import java.util.List;

/**
 * (TbRegion)表服务接口
 *
 * @author DongJinwei
 * @since 2022-09-03 14:21:54
 */
public interface TbRegionService extends IService<TbRegion> {

    List<TbRegion> listPage(int page, int size, String reName);

    int count(String reName);

    boolean save(TbRegion tbRegion);

    Boolean update(TbRegion tbRegion);

    List<String> getShortname();

    List<TbRegion> getName(String reShortname);


}

