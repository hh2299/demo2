package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.Task;
import com.example.demo.orm.mapper.TaskMapper;
import com.example.demo.orm.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-05-09
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
