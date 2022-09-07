package com.job.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
 * (TbUser)表实体类
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@TableName("tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbUser implements Serializable {
    //用户ID
    @TableId(type = IdType.AUTO)
    private Integer uId;
    //用户名称
    private String uName;
    //用户邮箱
    private String uEmail;
    //用户密码
    private String uPassword;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    //修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifyTime;
    //删除标记
    @TableLogic
    private Integer deleted;

}

