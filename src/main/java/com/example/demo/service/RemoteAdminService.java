package com.example.demo.service;

import com.example.demo.domain.dto.AdminDTO;
import com.example.demo.domain.dto.AttendanceDTO;

public interface RemoteAdminService {
    Long save(AdminDTO adminDTO);

    Long loginAdmin(AdminDTO adminDTO);

    AdminDTO getById(Long id);
}
