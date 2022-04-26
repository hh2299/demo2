package com.example.demo;

import com.example.demo.dao.HrMapping;
import com.example.demo.domain.dto.CompanyDTO;
import com.example.demo.domain.dto.HrDTO;
import com.example.demo.domain.param.CompanySearchParam;
import com.example.demo.domain.param.HrSearchParam;
import com.example.demo.orm.mapper.CompanyMapper;
import com.example.demo.orm.service.impl.HrServiceImpl;
import com.example.demo.service.RemoteCompanyService;
import com.example.demo.service.RemoteHrService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Demo2ApplicationTests {

	@Resource
	RemoteHrService hrService;
	@Resource
	RemoteCompanyService companyService;

	@Test
	void contextLoads() {
		List<HrDTO> hrDTOS = hrService.getHrList(new HrSearchParam());
		System.out.println(hrDTOS);

	}

	@Test
	void test1() {
		List<CompanyDTO> companyDTOS = companyService.getCompanyList(new CompanySearchParam());
		System.out.println(companyDTOS);

	}

}
