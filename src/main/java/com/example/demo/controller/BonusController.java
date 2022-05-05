package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.BonusDTO;
import com.example.demo.domain.dto.SalaryDTO;
import com.example.demo.domain.param.BonusSearchParam;
import com.example.demo.domain.param.SalarySearchParam;
import com.example.demo.service.RemoteBonusService;
import com.example.demo.service.RemoteSalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/bonus")
@Api("奖金接口")
public class BonusController {

    @Resource
    RemoteBonusService bonusService;

    @ApiOperation("任务列表")
    @PostMapping("/list")
    public BaseModel<List<BonusDTO>> getList(@RequestBody BonusSearchParam param) {
        List<BonusDTO> data = bonusService.getList(param);
        return BaseModel.buildSuccess(data);
    }
//
    @ApiOperation("保存")
    @PostMapping("/save")
    public BaseModel<Long> save(@RequestBody BonusDTO bonusDTO) {
        Long id = bonusService.save(bonusDTO);
        return BaseModel.buildSuccess(id);
    }

}
