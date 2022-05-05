package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.dao.AttendanceMapping;
import com.example.demo.domain.dto.ApplicantDTO;
import com.example.demo.domain.dto.AttendanceDTO;
import com.example.demo.domain.dto.AttendanceStatisticDTO;
import com.example.demo.domain.param.AttendanceSearchParam;
import com.example.demo.domain.param.LeaveSearchParam;
import com.example.demo.domain.param.StatisticSearchParam;
import com.example.demo.domain.vo.AttendanceVo;
import com.example.demo.orm.entity.*;
import com.example.demo.orm.mapper.AttendanceMapper;
import com.example.demo.orm.mapper.HireMapper;
import com.example.demo.service.RemoteAttendanceService;
import com.example.demo.service.RemoteCompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.demo.common.util.DateUtil.countWorkDay;

@Service
public class RemoteAttendanceServiceImpl extends BaseService implements RemoteAttendanceService {

    @Resource
    AttendanceMapper attendanceMapper;
    @Resource
    RemoteCompanyService companyService;
    @Resource
    HireMapper hireMapper;
    @Resource
    AttendanceMapping attendanceMapping;


    @Override
    public List<AttendanceVo> getList(AttendanceSearchParam param) {
        if (param == null) {
            throw new MyException("请完善你的查询参数");
        }
        LambdaQueryWrapper<Attendance> wrapper = new LambdaQueryWrapper<>();
        if (param.getCompanyId() != null) {
            wrapper.eq(Attendance::getCompanyId, param.getCompanyId());
        }
        if (param.getSignDate() != null) {
            Date startDate = getThisDaysStart(param.getSignDate());

            Date endDate = getThisDaysEnd(param.getSignDate());
            wrapper.ge(Attendance::getSignInTime, startDate).le(Attendance::getSignOutTime, endDate);
        }
        List<Attendance> attendanceList = attendanceMapper.selectList(wrapper);
        return ConverterUtils.convertList(attendanceList, AttendanceVo.class);
    }

    @Override
    public Long save(AttendanceDTO attendanceDTO) {
        if (attendanceDTO == null) {
            throw new MyException("请完善打卡信息");
        }

        Long id = attendanceDTO.getId();
        Attendance attendance = ConverterUtils.convert(attendanceDTO, Attendance.class);
        if (id == null) {
            attendanceMapper.insert(attendance);
            id = attendance.getId();
        } else {
            attendanceMapper.updateById(attendance);
        }
        return id;
    }

    @Override
    public List<AttendanceStatisticDTO> statistic(StatisticSearchParam param) {
        if (param == null) {
            throw new MyException("请完善查询信息");
        }
        List<ApplicantDTO> applicantDTOList = companyService.getEmployeeList(param.getCompanyId());
        if (applicantDTOList.size() == 0) {
            return new ArrayList<>();
        }
        List<AttendanceStatisticDTO> attendanceStatisticDTOList = new ArrayList<>();
        for (ApplicantDTO a : applicantDTOList) {
            AttendanceStatisticDTO attendanceStatisticDTO = getStatisticByApplicant(a, param.getStartDate(), param.getEndDate());
            if (attendanceStatisticDTO != null) {
                attendanceStatisticDTOList.add(attendanceStatisticDTO);
            }
        }

        return attendanceStatisticDTOList;
    }

    /**
     * 根据职员获取统计信息
     *
     * @param
     * @return
     */
    private AttendanceStatisticDTO getStatisticByApplicant(ApplicantDTO a, Date startDate, Date endDate) {

        //默认的start end
        Date tempStartDate = null;
        if (startDate == null && endDate == null) {
            endDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            startDate = calendar.getTime();
        }
        //获取入职日期
        LambdaQueryWrapper<Hire> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Hire::getApplicantId, a.getId());
        wrapper1.orderByDesc(Hire::getHireDate);
        List<Hire> hires = hireMapper.selectList(wrapper1);
        if (hires.size() == 0) {  //该职员辞退了
            return null;
        }
        tempStartDate = hires.get(0).getHireDate();
        endDate = endDate.after(new Date()) ? new Date() : endDate;
        startDate = startDate.after(tempStartDate) ? startDate : tempStartDate;

        startDate = getThisDaysEnd(startDate);
        endDate = getThisDaysEnd(endDate);
        int total = countWorkDay(startDate, endDate);
        if (total == 0) {
            return null;
        }

        AttendanceStatisticDTO attendanceStatistic = new AttendanceStatisticDTO();
        attendanceStatistic.setName(a.getName());

        //出勤天数

        List<Attendance> attendanceList = attendanceMapping.selectCount(startDate, endDate, a.getId());
        Integer attendanceCount = attendanceList.size();
        attendanceStatistic.setAttendance(attendanceCount*100/total);
        //缺勤天数
        attendanceStatistic.setAbsenteeism(100 - attendanceStatistic.getAttendance());

        //迟到天数
        int lateCount = 0;
        for (Attendance attendance : attendanceList) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(attendance.getSignInTime());
            if (calendar.get(Calendar.HOUR) >= 9) {
                lateCount++;
            }
        }
        attendanceStatistic.setLate(lateCount * 100 / total);


        //打卡异常

        int signErrorCount = 0;
        for (Attendance attendance : attendanceList) {
            if (attendance.getSignOutTime() == null || attendance.getSignInTime() == null) {
                signErrorCount++;
            }
        }
        attendanceStatistic.setSignError(signErrorCount);

        //请假
        LeaveSearchParam searchParam = new LeaveSearchParam();
        searchParam.setApplicantId(a.getId());
        searchParam.setStartDate(startDate);
        searchParam.setEndDate(endDate);
        searchParam.setIsAgreed(1);
        List<LeaveRecord> leaveList = attendanceMapping.selectLeaveList(searchParam);

        attendanceStatistic.setLeave(leaveList.size() * 100 / total);

        return attendanceStatistic;
    }



    private Date getThisDaysStart(Date thisDay){
        Calendar start = Calendar.getInstance();
        start.setTime(thisDay);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        Date startDate = start.getTime();
        return startDate;
    }

    private Date  getThisDaysEnd(Date thisDay){
        Calendar end = Calendar.getInstance();
        end.setTime(thisDay);
        end.add(Calendar.DAY_OF_MONTH, 1);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        Date endDate = end.getTime();
        return endDate;
    }
}
