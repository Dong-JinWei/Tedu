package com.job.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * (TbRegion)表实体类
 *
 * @author DongJinwei
 * @since 2022-09-03 13:46:11
 */
@TableName("tb_region")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbRegion implements Serializable {
    //主键
    @TableId(type = IdType.AUTO)
    private Integer reId;
    //地区名称
    private String reName;
    
    private String reShortname;
    //行政地区编号
    private Integer reCode;
    //地区级别 1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县
    private Integer reLevel;
    //所属上级地区编号
    private Integer reParentId;
    //区域编号外键
    @TableField(exist = false)
    private TbArea tbArea;
    // private Integer arId;

}

