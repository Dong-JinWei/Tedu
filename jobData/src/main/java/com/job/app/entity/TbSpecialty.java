package com.job.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
 * (TbSpecialty)表实体类
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@TableName("tb_specialty")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbSpecialty implements Serializable {
    //专业ID
    @TableId(type = IdType.AUTO)
    private Integer spId;
    //专业名称
    private String spName;
    //专业编号
    private String spNum;
    //院系外键
    @TableField(exist = false)
    private TbDept tbDept;
    // private Integer deId;
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

