package com.example.demo;

import com.example.demo.domain.dto.*;
import com.example.demo.domain.param.*;

import com.example.demo.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class Demo2ApplicationTests {

	@Resource
	RemoteTaskService taskService;
	@Resource
	RemoteReportService reportService;
	@Resource
	RemoteAttendanceService attendanceService;
	@Resource
	RemoteAdminService adminService;
	@Resource
	RemoteUserService userService;

	@Test
	void userTest() {
		UserDTO userDTO = new UserDTO();
//		userDTO
	}

	@Test
	void testAdmin() {
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setUsername("123456");
		adminDTO.setPassword("1234526");
		System.out.println(adminService.loginAdmin(adminDTO));
	}


	@Test
	void testAttendance() {
		StatisticSearchParam searchParam = new StatisticSearchParam();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,2022);
		calendar.set(Calendar.MONTH,3);
		calendar.set(Calendar.DAY_OF_MONTH,30);
		Date start = calendar.getTime();
		calendar.set(Calendar.YEAR,2022);
		calendar.set(Calendar.MONTH,4);
		calendar.set(Calendar.DAY_OF_MONTH,31);
		Date end = calendar.getTime();
		searchParam.setStartDate(start);
		searchParam.setEndDate(end);
		searchParam.setCompanyId(151959333677461L);
		System.out.println(attendanceService.statistic(searchParam));
	}


	@Test
	void test1() {
		Date date = new Date();
		System.out.println(date);

	}

	@Test
	void testTask() {
		TaskSearchParam searchParam = new TaskSearchParam();
		searchParam.setCompanyId(1519593336774610946L);
		List<TaskDTO> taskDTOS = taskService.getList(searchParam);
		System.out.println(taskDTOS);

	}
	@Test
	void testTaskInsert() {
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setApplicant("胡汉三");
		taskDTO.setCompanyId(1519593336774610946L);
		taskDTO.setApplicantId(1519612121065746433L);
		taskDTO.setContent("content");
		taskDTO.setIsPublished(0);
		taskDTO.setIsSummary(1);
		taskDTO.setStartDate(new Date());
		Long id = taskService.saveTask(taskDTO);
		System.out.println(id);
	}

	@Test
	void testTaskDelete() {
		taskService.deleteTask(1L);
	}

	@Test
	void testAddTaskRecord() {
		TaskRecordDTO taskRecordDTO = new TaskRecordDTO();
		taskRecordDTO.setTaskId(1519874498160005121L);
		taskRecordDTO.setRecordDate(new Date());
		taskRecordDTO.setRecordContent("做了一般");
		taskService.saveRecord(taskRecordDTO
		);
	}

	@Test
	void testRecordDelete() {
		taskService.deleteRecord(1519887066798792706L);
	}

	@Test
	void testInsertReport() {
		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setReportDate(new Date());
		reportDTO.setContent("我的第二次报告");
		reportDTO.setApplicantId(1519612121065746433L);
		reportDTO.setApplicant("胡汉三");
		reportDTO.setDescription("啊吧啊吧");
		reportDTO.setTopic("Topic");
		reportDTO.setCompanyId(1519593336774610946L);
		System.out.println(reportService.save(reportDTO));
	}

	@Test
	void testReportList() {
		ReportSearchParam searchParam = new ReportSearchParam();
		searchParam.setCompanyId(1519593336774610946L);
		System.out.println(reportService.getList(searchParam));
	}

	@Test
	void testReportDelete(){
		reportService.delete(1519891202625830914L);
	}

	public static void main(String[] args) {
		UUID uuid1 = UUID.randomUUID();
		System.out.println("UUID : "+uuid1);
		System.out.println("UUID Version : "+uuid1.version());
		System.out.println("UUID Variant : "+uuid1.variant());

		UUID uuid2 = UUID.randomUUID();
		System.out.println("UUID : "+uuid2);
		System.out.println("UUID Version : "+uuid2.version());
		System.out.println("UUID Variant : "+uuid2.variant());
	}
}
