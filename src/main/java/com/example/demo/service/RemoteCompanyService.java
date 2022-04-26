package com.example.demo.service;

import com.example.demo.domain.dto.CompanyDTO;
import com.example.demo.domain.param.CompanySearchParam;

import java.util.List;

public interface RemoteCompanyService {

//    查询公司列表
    List<CompanyDTO> getCompanyList(CompanySearchParam param);

//    保存公司
    Long save(CompanyDTO company);

//    根据指定id删除公司
    Boolean delete(Long id);

//    根据指定的id获取公司信息
    CompanyDTO getCompanyById(Long id);
}
