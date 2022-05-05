package com.example.demo.service;

import com.example.demo.domain.dto.BonusDTO;
import com.example.demo.domain.param.BonusSearchParam;

import java.util.List;

public interface RemoteBonusService {

    List<BonusDTO> getList(BonusSearchParam param);

    Long save(BonusDTO bonusDTO);
}
