package com.job.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.job.app.dao.TbUserDao;
import com.job.app.entity.TbUser;
import com.job.app.service.TbUserService;
import org.springframework.stereotype.Service;

/**
 * (TbUser)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-09-03 14:22:07
 */
@Service("tbUserService")
public class TbUserServiceImpl extends ServiceImpl<TbUserDao, TbUser> implements TbUserService {

}

