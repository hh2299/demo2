package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.domain.dto.DimissionDTO;
import com.example.demo.domain.param.DimissionSearchParam;
import com.example.demo.domain.vo.DimissionVo;
import com.example.demo.orm.entity.Dimission;
import com.example.demo.orm.mapper.DimissionMapper;
import com.example.demo.service.RemoteApplicantService;
import com.example.demo.service.RemoteDimissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RemoteDimissionServiceImpl extends BaseService implements RemoteDimissionService {

    @Resource
    DimissionMapper dimissionMapper;
    @Resource
    RemoteApplicantService applicantService;

    @Override
    public List<DimissionVo> getList(DimissionSearchParam param) {
        if (param == null) {
            throw new MyException("请完善查询参数");
        }
        LambdaQueryWrapper<Dimission> wrapper = new LambdaQueryWrapper<Dimission>();
        if (param.getCompanyId() != null) {
            wrapper.eq(Dimission::getCompanyId, param.getCompanyId());
        }
        if (param.getIsAgreed() != null) {
            wrapper.eq(Dimission::getIsAgreed, param.getIsAgreed());
        }
        List<Dimission> dimissionDTOS = dimissionMapper.selectList(wrapper);
        return ConverterUtils.convertList(dimissionDTOS, DimissionVo.class);
    }

    @Override
    public Long save(DimissionDTO dimissionDTO) {
        if (dimissionDTO == null) {
            throw new MyException("请完善离职申请信息");
        }
        Long id = dimissionDTO.getId();
        Dimission dimission = ConverterUtils.convert(dimissionDTO, Dimission.class);
        if (id == null) {
            dimissionMapper.insert(dimission);
            id = dimission.getId();
        } else {
            dimissionMapper.updateById(dimission);
        }
        if (dimission.getIsAgreed()!=null&&dimission.getIsAgreed() == 1) {
            //离职
            applicantService.termination(dimissionDTO.getApplicantId());
        }
        return id;
    }
}
