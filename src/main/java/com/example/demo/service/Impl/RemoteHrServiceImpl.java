package com.example.demo.service.Impl;

import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.domain.dto.HrDTO;
import com.example.demo.domain.param.HrSearchParam;
import com.example.demo.orm.entity.Company;
import com.example.demo.orm.entity.Hr;
import com.example.demo.orm.mapper.HrMapper;
import com.example.demo.dao.HrMapping;
import com.example.demo.service.RemoteHrService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<HrDTO> HrDTOList = hrMapping.getHrList(param, null);
        return HrDTOList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(HrDTO hrDTO) {
        if (hrDTO == null) {
            throw new MyException("没有保存信息");
        }

        Long id = hrDTO.getId();

        Hr hr = ConverterUtils.convert(hrDTO, Hr.class);
        if (id == null) {
            hrMapper.insert(hr);
            id = hr.getId();
        } else {
            hrMapper.updateById(hr);
        }

        return id;
    }

    @Override
    public Boolean delete(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }

        int count = hrMapper.deleteById(id);

        return count == 1;
    }

    @Override
    public HrDTO getHrById(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }

        List<HrDTO> hrDTOs = hrMapping.getHrList(new HrSearchParam(),id);
        if (hrDTOs.size() != 1) {
            throw new MyException("该Hr信息有错误");
        }

        return hrDTOs.get(0);
    }
}
