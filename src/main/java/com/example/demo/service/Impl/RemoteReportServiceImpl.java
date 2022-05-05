package com.example.demo.service.Impl;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.mp.page.annotation.EnablePage;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.dao.TaskMapping;
import com.example.demo.domain.dto.ReportDTO;
import com.example.demo.domain.param.ReportSearchParam;
import com.example.demo.orm.entity.Report;
import com.example.demo.orm.mapper.ReportMapper;
import com.example.demo.service.RemoteReportService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RemoteReportServiceImpl extends BaseService implements RemoteReportService {

    @Resource
    ReportMapper reportMapper;
    @Resource
    TaskMapping taskMapping;

    @Override
    @EnablePage
    public List<ReportDTO> getList(ReportSearchParam param) {
        if (param == null) {
            throw new MyException("请完善查询信息");
        }

        List<ReportDTO> reports = taskMapping.getReportList(param);

        return reports;
    }


    @Override
    public Long save(ReportDTO reportDTO) {
        if (reportDTO == null) {
            throw new MyException("请完善报告信息");
        }

        Long id = reportDTO.getId();
        Report report = ConverterUtils.convert(reportDTO, Report.class);
        if (id == null) {
            reportMapper.insert(report);
            id = report.getId();
        } else {
            reportMapper.updateById(report);
        }
        return id;
    }

    @Override
    public Boolean delete(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }

        int count = reportMapper.deleteById(id);

        return count == 1;
    }
}
