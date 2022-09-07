package com.job.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.job.app.entity.TbSpecialty;

import java.util.List;

/**
 * (TbSpecialty)表服务接口
 *
 * @author DongJinwei
 * @since 2022-09-03 14:21:54
 */
public interface TbSpecialtyService extends IService<TbSpecialty> {

    List<TbSpecialty> listPage(int page, int size, String spName);

    int count(String spName);

    boolean save(TbSpecialty tbSpecialty);

    Boolean update(TbSpecialty tbSpecialty);

}

