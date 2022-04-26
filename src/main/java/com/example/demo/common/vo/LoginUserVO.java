package com.example.demo.common.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel("用户登录")
@Data
public class LoginUserVO implements Serializable {

    private static final long serialVersionUID = 1809087524306643069L;

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

    @ApiModelProperty(value = "角色ID")
    private String positionId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户名")
    private String nickName;

    @ApiModelProperty(value = "姓名(英文)")
    private String nickNameEn;

    @ApiModelProperty(value = "用户Token")
    private String userToken;

    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "职位")
    private String position;

    private String password;
    @ApiModelProperty(value = "账号状态")
    private String status;

    @ApiModelProperty(value = "菜单权限")
    private List<String> menuAuths;

    @ApiModelProperty(value = "拥有楼栋权限")
    private List<String> buildingList;

    @ApiModelProperty(value = "拥有项目权限")
    private List<String> projectList;

    @ApiModelProperty(value = "是否是管理员")
    private boolean admin;

    @ApiModelProperty(value = "是否拥有所有项目权限")
    private boolean allProject;


}

