package com.job.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.job.app.entity.TbStudent;
import com.job.app.entity.Top5;

import java.util.List;

/**
 * (TbStudent)表服务接口
 *
 * @author DongJinwei
 * @since 2022-09-03 14:21:54
 */
public interface TbStudentService extends IService<TbStudent> {

    List<TbStudent> listPage(int page, int size, String sName);

    int count(String sName);

    Boolean update(TbStudent tbStudent);

    boolean save(TbStudent tbStudent);

    boolean updateImagesInt(TbStudent tbStudent);

    List<Top5> getTop5();

}

