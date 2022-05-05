package com.example.demo.service;

import com.example.demo.domain.dto.TaskDTO;
import com.example.demo.domain.dto.TaskRecordDTO;
import com.example.demo.domain.param.TaskSearchParam;

import java.util.List;

public interface RemoteTaskService {
    /**
     * 获取任务列表
     * @param param
     * @return
     */
    List<TaskDTO> getList(TaskSearchParam param);

    /**
     * 保存或发布任务
     * @param taskDTO
     * @return
     */
    Long saveTask(TaskDTO taskDTO);

    /**
     * 删除任务
     * @param id
     * @return
     */
    Boolean deleteTask(Long id);

    /**
     * 保存任务记录
     * @param taskRecord
     * @return
     */
    Long saveRecord(TaskRecordDTO taskRecord);

    /**
     * 删除任务记录
     * @param id
     * @return
     */
    Boolean deleteRecord(Long id);
}
