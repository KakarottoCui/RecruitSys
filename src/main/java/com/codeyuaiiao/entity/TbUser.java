package com.codeyuaiiao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value="TbUser对象", description="")
@TableName(value = "tb_user")
public class TbUser extends Model<TbUser> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @TableField(value = "username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField(value = "password")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "年龄")
    @TableField(value = "age")
    private String age;

    @ApiModelProperty(value = "性别")
    @TableField(value = "gender")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    @TableField(value = "birthday")
    private String birthday;

    @ApiModelProperty(value = "毕业学校")
    @TableField(value = "school")
    private String school;

    @ApiModelProperty(value = "联系电话")
    @TableField(value = "phone")
    private String phone;

    @ApiModelProperty(value = "电子邮箱")
    @TableField(value = "email")
    private String email;

    @ApiModelProperty(value = "所学专业")
    @TableField(value = "specialty")
    private String specialty;

    @ApiModelProperty(value = "最高学历")
    @TableField(value = "education")
    private String education;

//    @ApiModelProperty(value = "头像")
//    @TableField(value = "headImg")
//    private String headImg;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
