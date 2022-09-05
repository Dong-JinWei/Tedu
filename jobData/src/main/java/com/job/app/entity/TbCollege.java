package com.job.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
 * (TbCollege)表实体类
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@TableName("tb_college")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbCollege implements Serializable {
    //院校ID
    @TableId(type = IdType.AUTO)
    private Integer coId;
    //院校名称
    private String coName;
    //院校地址
    private String coAddr;
    //院校编号
    private String coNum;
    //院校邮箱
    private String coEmail;
    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    //修改时间
    @TableField(fill = FieldFill.UPDATE)
    private String modifyTime;
    //删除标记
    @TableLogic
    private Integer deleted;

}

