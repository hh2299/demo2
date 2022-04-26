package com.example.demo.common.entity;

import com.example.demo.common.util.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: DaleShay
 * @Date: 2020/6/17 13:58
 * @Description:
 */
@Data
public class DocDTO extends BaseDTO{

    private static final long serialVersionUID = -2033032751730996402L;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "文件uid")
    private String uid;

    @ApiModelProperty(value = "链接")
    private String url;

    @ApiModelProperty(value = "类型")
    private String type;

    public String getUid() {
        if(StringUtil.isNull(uid)){
            if(id != null) {
                return this.id.toString();
            }
        }
        return uid;
    }
}
