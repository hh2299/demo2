package com.example.demo.service.Impl;

import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.dao.PerformanceMapping;
import com.example.demo.domain.dto.PerformanceDTO;
import com.example.demo.domain.param.PerformanceSearchParam;
import com.example.demo.orm.entity.Performance;
import com.example.demo.orm.mapper.PerformanceMapper;
import com.example.demo.service.RemotePerformanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RemotePerformanceServiceImpl extends BaseService implements RemotePerformanceService {

    @Resource
    PerformanceMapping performanceMapping;

    @Override
    public List<PerformanceDTO> getList(PerformanceSearchParam param) {
        if (param == null) {
            throw new MyException("没有查询参数");
        }

        List<PerformanceDTO> performanceList = performanceMapping.getList(param);

        return performanceList;
    }
}
