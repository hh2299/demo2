package com.example.demo.service;

import com.example.demo.domain.dto.LeaveDTO;
import com.example.demo.domain.dto.LeaveStaticDTO;
import com.example.demo.domain.param.LeaveSearchParam;
import com.example.demo.domain.vo.LeaveVo;

import java.util.List;

public interface RemoteLeaveService {

    List<LeaveVo> getList(LeaveSearchParam param);

    Long save(LeaveDTO leaveDTO);

    List<LeaveStaticDTO> statistic(LeaveSearchParam param);
}
