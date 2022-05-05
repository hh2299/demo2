package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.domain.dto.ApplicantDTO;
import com.example.demo.domain.dto.LeaveDTO;
import com.example.demo.domain.dto.LeaveStaticDTO;
import com.example.demo.domain.param.LeaveSearchParam;
import com.example.demo.domain.vo.LeaveVo;
import com.example.demo.orm.entity.Hire;
import com.example.demo.orm.entity.LeaveRecord;
import com.example.demo.orm.mapper.HireMapper;
import com.example.demo.orm.mapper.LeaveRecordMapper;
import com.example.demo.service.RemoteCompanyService;
import com.example.demo.service.RemoteLeaveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.demo.common.util.DateUtil.countWorkDay;

@Service
public class RemoteLeaveServiceImpl extends BaseService implements RemoteLeaveService {
    @Resource
    LeaveRecordMapper leaveRecordMapper;
    @Resource
    RemoteCompanyService companyService;
    @Resource
    HireMapper hireMapper;

    @Override
    public List<LeaveVo > getList(LeaveSearchParam param) {
        if (param == null) {
            throw new MyException("请完善查询参数");
        }
        LambdaQueryWrapper<LeaveRecord> wrapper = new LambdaQueryWrapper<>();
        if (param.getCompanyId() != null) {
            wrapper.eq(LeaveRecord::getCompanyId, param.getCompanyId());
        }
        if (param.getIsAgreed() != null) {
            wrapper.eq(LeaveRecord::getIsAgreed, param.getIsAgreed());
        } else {
            wrapper.isNull(LeaveRecord::getIsAgreed);
        }
        List<LeaveRecord> leaves = leaveRecordMapper.selectList(wrapper);
        return ConverterUtils.convertList(leaves, LeaveVo.class);
    }

    @Override
    public Long save(LeaveDTO leaveDTO) {
        if (leaveDTO == null) {
            throw new MyException("请完善请假信息");
        }
        Long id = leaveDTO.getId();
        LeaveRecord leave = ConverterUtils.convert(leaveDTO, LeaveRecord.class);
        if (id == null) {
            leaveRecordMapper.insert(leave);
            id = leave.getId();
        } else {
            leaveRecordMapper.updateById(leave);
        }
        return id;
    }

    @Override
    public List<LeaveStaticDTO> statistic(LeaveSearchParam param) {
        if (param == null) {
            throw new MyException("请完善查询信息");
        }
        List<ApplicantDTO> applicantDTOList = companyService.getEmployeeList(param.getCompanyId());
        if (applicantDTOList.size() == 0) {
            return new ArrayList<>();
        }
        List<LeaveStaticDTO> leaveStaticDTOS = new ArrayList<>();
        for (ApplicantDTO a : applicantDTOList) {
            LeaveStaticDTO leaveStaticDTO = getStatisticByApplicant(a, param.getStartDate(), param.getEndDate());
            if (leaveStaticDTO != null) {
                leaveStaticDTOS.add(leaveStaticDTO);
            }
        }
        return leaveStaticDTOS;
    }

    private LeaveStaticDTO getStatisticByApplicant(ApplicantDTO a, Date startDate, Date endDate) {

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

        LeaveStaticDTO leaveStaticDTO = new LeaveStaticDTO();
        leaveStaticDTO.setName(a.getName());

        LambdaQueryWrapper<LeaveRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LeaveRecord::getApplicantId, a.getId());
        wrapper.ge(LeaveRecord::getStartTime, startDate);
        wrapper.le(LeaveRecord::getEndTime, endDate);
        List<LeaveRecord> leaveList = leaveRecordMapper.selectList(wrapper);
        leaveStaticDTO.setLeaveCount(leaveList.size());
        leaveStaticDTO.setLeaveRate(leaveList.size() * 100 / total);

        int sickCount = 0;
        int affairsCount = 0;
        for (LeaveRecord leave : leaveList) {
            if ("病假".equals(leave.getType())) {
                sickCount++;
            }
            if ("事假".equals(leave.getType())) {
                affairsCount++;
            }
        }
        leaveStaticDTO.setSickCount(sickCount);
        leaveStaticDTO.setAffairsCount(affairsCount);

        return leaveStaticDTO;
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
