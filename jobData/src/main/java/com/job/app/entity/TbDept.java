package com.job.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
 * (TbDept)表实体类
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@TableName("tb_dept")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbDept implements Serializable {
    //院系ID
    @TableId(type = IdType.AUTO)
    private Integer deId;
    //院系名称
    private String deName;
    //院系编号
    private String deNum;
    //院校外键
    @TableField(exist = false)
    private TbCollege tbCollege;
    // private Integer coId;
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

