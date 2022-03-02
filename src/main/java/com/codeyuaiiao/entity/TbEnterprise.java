package com.codeyuaiiao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="TbEnterprise对象", description="")
public class TbEnterprise extends Model<TbEnterprise> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "企业名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "电子邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "联系电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "从事行业")
    @TableField("manage")
    private String manage;

    @ApiModelProperty(value = "企业地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "公司简介")
    @TableField("introduce")
    private String introduce;

//    @ApiModelProperty(value = "头像")
//    @TableField("headImg")
//    private String headImg;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
