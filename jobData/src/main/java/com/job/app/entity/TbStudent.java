package com.job.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
 * (TbStudent)表实体类
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@TableName("tb_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbStudent implements Serializable {
    //学生ID
    @TableId(type = IdType.AUTO)
    private Integer sId;
    //学生姓名
    private String sName;
    //学生学号
    private String sNum;
    //学生性别
    private String sSex;
    //出生日期
    private String sBirthday;
    //学生照片
    private String sPhoto;
    //班级外键
    @TableField(exist = false)
    private TbClazz tbClazz;
    // private Integer clId;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    //修改时间
    @TableField(fill = FieldFill.UPDATE)
    private String modifyTime;
    //删除标记
    @TableLogic
    private Integer deleted;

}

