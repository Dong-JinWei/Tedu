package com.job.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
 * (TbJob)表实体类
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@TableName("tb_job")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbJob implements Serializable {
    //就业ID
    @TableId(type = IdType.AUTO)
    private Integer jId;
    //学生ID
    @TableField(exist = false)
    private TbStudent tbStudent;
    // private Integer sId;
    //企业ID
    @TableField(exist = false)
    private TbCompany tbCompany;
    // private Integer cpId;
    //就业薪资
    private Double jSalary;
    //就业方向ID
    @TableField(exist = false)
    private TbDirection tbDirection;
    // private Integer diId;
    //三方协议ID
    @TableField(exist = false)
    private TbProtocol tbProtocol;
    // private Integer proId;
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

