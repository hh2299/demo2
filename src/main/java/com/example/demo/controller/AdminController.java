package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.AdminDTO;
import com.example.demo.domain.dto.AttendanceDTO;
import com.example.demo.service.RemoteAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
@Api("管理员接口")
public class AdminController {
    @Resource
    RemoteAdminService adminService;

    @ApiOperation("保存管理员")
    @PostMapping("/save")
    public BaseModel<Long> save(@RequestBody AdminDTO adminDTO) {
        Long data = adminService.save(adminDTO);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public BaseModel<Long> login(@RequestBody AdminDTO adminDTO) {
        Long data = adminService.loginAdmin(adminDTO);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("获取指定管理员")
    @GetMapping("/getById")
    public BaseModel<AdminDTO> getById(@RequestParam Long id) {
        AdminDTO data = adminService.getById(id);
        return BaseModel.buildSuccess(data);
    }

}
