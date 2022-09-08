package com.job.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.job.app.entity.TbClazz;

import java.util.List;

/**
 * (TbClazz)表服务接口
 *
 * @author DongJinwei
 * @since 2022-09-03 14:21:54
 */
public interface TbClazzService extends IService<TbClazz> {

    int count(String clName);

    List<TbClazz> listPage(int page, int size, String clName);

    Boolean update(TbClazz tbClazz);

    boolean save(TbClazz tbClazz);

    List<TbClazz> getClazzNames(Integer spId);


}

