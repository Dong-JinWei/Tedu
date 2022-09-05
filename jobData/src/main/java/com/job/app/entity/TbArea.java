package com.job.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * (TbArea)表实体类
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@TableName("tb_area")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("区域")
public class TbArea implements Serializable {
    //区域ID
    private Integer arId;
    //区域名称
    private String arName;
    //区域描述
    private String arDesc;

}

