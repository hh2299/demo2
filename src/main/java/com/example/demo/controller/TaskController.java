package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.dao.TaskMapping;
import com.example.demo.domain.dto.RecruitDTO;
import com.example.demo.domain.dto.TaskDTO;
import com.example.demo.domain.dto.TaskRecordDTO;
import com.example.demo.domain.param.RecruitSearchParam;
import com.example.demo.domain.param.TaskSearchParam;
import com.example.demo.service.RemoteTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/task")
@Api("任务接口")
public class TaskController {

    @Resource
    RemoteTaskService taskService;


    @ApiOperation("任务列表")
    @PostMapping("/list")
    public BaseModel<List<TaskDTO>> getList(@RequestBody TaskSearchParam param) {
        List<TaskDTO> data = taskService.getList(param);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("保存")
    @PostMapping("/saveTask")
    public BaseModel<Long> saveTask(@RequestBody TaskDTO taskDTO) {
        Long data = taskService.saveTask(taskDTO);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("删除任务")
    @GetMapping("/deleteTask")
    public BaseModel<Boolean> deleteTask(@RequestParam Long id) {
        Boolean data = taskService.deleteTask(id);
        return data ? BaseModel.buildSuccess() : BaseModel.buildError();
    }

    @ApiOperation("任务记录")
    @PostMapping("/saveRecord")
    public BaseModel<Long> saveRecord(@RequestBody TaskRecordDTO taskRecord) {
        Long data = taskService.saveRecord(taskRecord);
        return BaseModel.buildSuccess(data);
    }


    @ApiOperation("删除任务记录")
    @GetMapping("/deleteRecord")
    public BaseModel<Boolean> deleteRecord(@RequestParam Long id) {
        Boolean data = taskService.deleteRecord(id);
        return data ? BaseModel.buildSuccess() : BaseModel.buildError();
    }
}
