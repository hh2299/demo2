package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.param.UserSearchParam;
import com.example.demo.service.RemoteUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags="用户接口")
public class UserController {

    @Resource
    RemoteUserService userService;


    @ApiOperation("user列表")
    @PostMapping("/list")
    public BaseModel<List<UserDTO>> getHrList(@RequestBody UserSearchParam param) {

        List<UserDTO> data = userService.getUserList(param);
        return BaseModel.buildSuccess(data);
    }


    @ApiOperation("保存user")
    @PostMapping("/save")
    public BaseModel<Long> saveUser(@RequestBody UserDTO company) {
        Long id = userService.save(company);
        return BaseModel.buildSuccess(id);
    }

    @ApiOperation("删除User")
    @GetMapping("/delete")
    public BaseModel<UserDTO> delete(@RequestParam Long id) {
        Boolean data = userService.delete(id);
        return data ? BaseModel.buildSuccess() : BaseModel.buildError();
    }

    @ApiOperation("根据User的id获取指定Hr信息")
    @GetMapping("getById")
    public BaseModel<UserDTO> getById(@RequestParam Long id) {
        UserDTO data = userService.getUserById(id);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("user登录")
    @PostMapping("/login")
    public BaseModel<UserDTO> login(@RequestBody UserDTO userDTO) {
        UserDTO data = userService.login(userDTO);
        return BaseModel.buildSuccess(data);
    }


}
