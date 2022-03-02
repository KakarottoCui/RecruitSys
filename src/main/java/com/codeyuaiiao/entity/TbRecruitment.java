package com.codeyuaiiao.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TbRecruitment对象", description="")
public class TbRecruitment extends Model<TbRecruitment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业编号")
    @TableId(value ="id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "企业名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "所属行业")
    @TableField("industry")
    private String industry;

    @ApiModelProperty(value = "招聘职位")
    @TableField("job")
    private String job;

    @ApiModelProperty(value = "招聘薪水")
    @TableField("salary")
    private String salary;

    @ApiModelProperty(value = "工作地点")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "发布时间")
    @TableField("release_time")
    private LocalDateTime releaseTime;

    @ApiModelProperty(value = "有效时间")
    @TableField("valid_time")
    private String validTime;

    @ApiModelProperty(value = "发布网站")
    @TableField("web")
    private String web;

    @ApiModelProperty(value = "学历")
    @TableField("education")
    private String education;

    @ApiModelProperty(value = "经验")
    @TableField("experience")
    private String experience;

    @ApiModelProperty(value = "招聘人数")
    @TableField("number")
    private String number;

    @ApiModelProperty(value = "职位描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "公司简介")
    @TableField("company_profile")
    private String companyProfile;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
