package com.job.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
 * (TbProtocol)表实体类
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@TableName("tb_protocol")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbProtocol implements Serializable {
    //协议ID
    @TableId(type = IdType.AUTO)
    private Integer proId;
    //协议名称
    private String proName;
    //协议大小
    private Integer proSize;
    //存储URL
    private String proUrl;
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

