package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.TaskRecord;
import com.example.demo.orm.mapper.TaskRecordMapper;
import com.example.demo.orm.service.ITaskRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-04-29
 */
@Service
public class TaskRecordServiceImpl extends ServiceImpl<TaskRecordMapper, TaskRecord> implements ITaskRecordService {

}
