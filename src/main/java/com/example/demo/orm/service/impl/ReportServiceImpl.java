package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.Report;
import com.example.demo.orm.mapper.ReportMapper;
import com.example.demo.orm.service.IReportService;
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
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {

}
