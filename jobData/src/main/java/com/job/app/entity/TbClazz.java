package com.job.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
 * (TbClazz)表实体类
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@TableName("tb_clazz")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbClazz implements Serializable {
    //班级ID
    @TableId(type = IdType.AUTO)
    private Integer clId;
    //班级名称
    private String clName;
    //班级编号
    private String clNum;
    //班级年级
    private String clGrade;
    //专业外键
    @TableField(exist = false)
    private TbSpecialty tbSpecialty;
    // private Integer spId;
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

