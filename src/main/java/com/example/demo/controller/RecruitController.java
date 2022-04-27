package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.RecruitDTO;
import com.example.demo.domain.param.RecruitSearchParam;
import com.example.demo.service.RemoteRecruitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/recruit")
@Api("招聘接口")
public class RecruitController {

    @Resource
    RemoteRecruitService recruitService;

    @ApiOperation("招聘列表")
    @PostMapping("/list")
    public BaseModel<List<RecruitDTO>> getList(@RequestBody RecruitSearchParam param) {
        List<RecruitDTO> data = recruitService.getRecruitList(param);
        return BaseModel.buildSuccess(data);
    }


    @ApiOperation("保存招聘")
    @PostMapping("/save")
    public BaseModel<Long> saveRecruit(@RequestBody RecruitDTO recruit) {
        Long id = recruitService.save(recruit);
        return BaseModel.buildSuccess(id);
    }

    @ApiOperation("删除Job")
    @GetMapping("delete")
    public BaseModel<RecruitDTO> delete(@RequestParam Long id) {
        Boolean data = recruitService.delete(id);
        return data ? BaseModel.buildSuccess() : BaseModel.buildError();
    }

    @ApiOperation("根据Job的id获取指定Job信息")
    @GetMapping("getById")
    public BaseModel<RecruitDTO> getById(@RequestParam Long id) {
        RecruitDTO data = recruitService.getRecruitById(id);
        return BaseModel.buildSuccess(data);
    }

}
