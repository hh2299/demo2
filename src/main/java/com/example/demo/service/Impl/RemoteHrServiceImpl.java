package com.example.demo.service.Impl;

import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.domain.dto.HrDTO;
import com.example.demo.domain.param.HrSearchParam;
import com.example.demo.orm.mapper.HrMapper;
import com.example.demo.dao.HrMapping;
import com.example.demo.service.RemoteHrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RemoteHrServiceImpl extends BaseService implements RemoteHrService {

    @Resource
    HrMapper hrMapper;

    @Resource
    HrMapping hrMapping;

    @Override
    public List<HrDTO> getHrList(HrSearchParam param) {
        if (param == null) {
            throw new MyException("没有查询参数");
        }
        List<HrDTO> HrDTOList = hrMapping.getHrList(param);
        return HrDTOList;
    }

    @Override
    public Long save(HrDTO hrDTO) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public HrDTO getHrById(Long id) {
        return null;
    }
}
