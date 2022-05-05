package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.LeaveRecord;
import com.example.demo.orm.mapper.LeaveRecordMapper;
import com.example.demo.orm.service.ILeaveRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-05-03
 */
@Service
public class LeaveRecordServiceImpl extends ServiceImpl<LeaveRecordMapper, LeaveRecord> implements ILeaveRecordService {

}
