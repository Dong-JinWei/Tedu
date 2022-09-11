package com.job.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.job.app.entity.TbStudent;
import com.job.app.entity.Top5;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TbStudent)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-09-03 14:26:05
 */
@Repository
public interface TbStudentDao extends BaseMapper<TbStudent> {

    List<TbStudent> listPage(int page, int size, String sName);

    int count(String sName);

    int update(TbStudent tbStudent);

    int save(TbStudent tbStudent);

    int updateImagesInt(TbStudent tbStudent);

    List<Top5> getTop5();
}

