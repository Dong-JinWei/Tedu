package com.job.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
 * (TbCompany)表实体类
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@TableName("tb_company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbCompany implements Serializable {
    //企业ID
    @TableId(type = IdType.AUTO)
    private Integer cpId;
    //企业名称
    private String cpName;
    //企业描述
    private String cpDesc;
    //地区外键
    @TableField(exist = false)
    private TbRegion tbRegion;
    // private Integer reId;
    //所在地址
    private String cpAddr;
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

