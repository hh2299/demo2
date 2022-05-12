package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.ReportDTO;
import com.example.demo.domain.dto.TaskDTO;
import com.example.demo.domain.param.ReportSearchParam;
import com.example.demo.domain.param.TaskSearchParam;
import com.example.demo.service.RemoteReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/report")
@Api(tags="报告接口")
public class ReportController {

    @Resource
    RemoteReportService reportService;

    @ApiOperation("报告列表")
    @PostMapping("/list")
    public BaseModel<List<ReportDTO>> getList(@RequestBody ReportSearchParam param) {
        List<ReportDTO> data = reportService.getList(param);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public BaseModel<Long> save(@RequestBody ReportDTO reportDTO) {
        Long data = reportService.save(reportDTO);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("删除报告")
    @GetMapping("/delete")
    public BaseModel<Boolean> delete(@RequestParam Long id) {
        Boolean data = reportService.delete(id);
        return data ? BaseModel.buildSuccess() : BaseModel.buildError();
    }
}
