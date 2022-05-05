package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.Attendance;
import com.example.demo.orm.mapper.AttendanceMapper;
import com.example.demo.orm.service.IAttendanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-05-02
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements IAttendanceService {

}
