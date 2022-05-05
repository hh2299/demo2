package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.mp.page.annotation.EnablePage;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.dao.TaskMapping;
import com.example.demo.domain.dto.ApplicantDTO;
import com.example.demo.domain.dto.TaskDTO;
import com.example.demo.domain.dto.TaskRecordDTO;
import com.example.demo.domain.param.TaskSearchParam;
import com.example.demo.orm.entity.Task;
import com.example.demo.orm.entity.TaskRecord;
import com.example.demo.orm.mapper.TaskMapper;
import com.example.demo.orm.mapper.TaskRecordMapper;
import com.example.demo.orm.service.impl.ApplicantServiceImpl;
import com.example.demo.service.RemoteApplicantService;
import com.example.demo.service.RemoteTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RemoteTaskServiceImpl extends BaseService implements RemoteTaskService {

    @Resource
    TaskMapper taskMapper;
    @Resource
    TaskMapping taskMapping;
    @Resource
    RemoteApplicantService applicantService;
    @Resource
    TaskRecordMapper taskRecordMapper;

    @Override
    @EnablePage
    public List<TaskDTO> getList(TaskSearchParam param) {
        if (param == null) {
            throw new MyException("没有查询参数");
        }

        List<TaskDTO> taskDTOS = taskMapping.getList(param);

        return taskDTOS;
    }

    @Override
    @Transactional
    public Long saveTask(TaskDTO taskDTO) {
        if (taskDTO == null) {
            throw new MyException("请完善任务信息");
        }

        Long applicantId = taskDTO.getApplicantId();
        if (applicantId == null) {
            throw new MyException("请完善任务员工信息");
        }
        ApplicantDTO applicant = applicantService.getById(applicantId);
        if (applicant == null || applicant.getIsHired() == 0) {
            throw new MyException("非本公司员工");
        }

        Task task = ConverterUtils.convert(taskDTO, Task.class);
        if (task.getIsPublished() == 1 && task.getStartDate() == null) {
            task.setStartDate(new Date());
        }
        Long id = task.getId();
        if (id == null) {
            taskMapper.insert(task);
            id = task.getId();
        } else {
            taskMapper.updateById(task);
        }

        return id;
    }

    @Override
    public Boolean deleteTask(Long id) {
        if (id == null) {
            throw new MyException("请完善指定的id");
        }

        super.deleteRelationList(taskRecordMapper, TaskRecord::getTaskId, id);

        int count = taskMapper.deleteById(id);

        return count == 1;
    }

    @Override
    public Long saveRecord(TaskRecordDTO taskRecordDTO) {
        if (taskRecordDTO == null) {
            throw new MyException("请完善任务记录");
        }

        Long taskId = taskRecordDTO.getTaskId();
        Task task = taskMapper.selectById(taskId);
        if (task == null || task.getIsPublished() == 0) {
            throw new MyException("任务不存在或者未发布");
        }
        Long id = taskRecordDTO.getId();
        TaskRecord taskRecord = ConverterUtils.convert(taskRecordDTO, TaskRecord.class);
        if (id == null) {
            taskRecordMapper.insert(taskRecord);
            id = taskRecord.getId();
        } else {
            taskRecordMapper.updateById(taskRecord);
        }
        return id;
    }

    @Override
    public Boolean deleteRecord(Long id) {
        if (id == null) {
            throw new MyException("请完善指定的id");
        }

        int count = taskRecordMapper.deleteById(id);

        return count == 1;
    }
}
