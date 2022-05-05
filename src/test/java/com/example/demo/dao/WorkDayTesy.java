package com.example.demo.dao;

import java.util.Calendar;
import java.util.Date;

public class WorkDayTesy {

    public static int countWorkDays(Date start, Date end) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);					//放入Date类型数据
        int startYear = calendar.get(Calendar.YEAR);					//获取年份
        int startMonth = calendar.get(Calendar.MONTH);
        int startDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTime(end);
        int endYear = calendar.get(Calendar.YEAR);					//获取年份
        int endMonth = calendar.get(Calendar.MONTH);
        int endDay = calendar.get(Calendar.DAY_OF_MONTH);
        int count = 0;
        if (startMonth == endMonth && startYear == endYear) {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(start);
            for (; startDay <= endDay; startDay++) {
                calendar1.set(Calendar.DAY_OF_MONTH, startDay);
                if (isWorkDay(calendar1)) {
                    count++;
                }
            }
        }
        for (int i = startYear;i<=endYear;i++){
            for (int j=startMonth;j<endMonth||i<endYear;j++){
                count += countMonthsWorkDay(i, j);
            }
        }
        return count;
    }


    public static int countMonthsWorkDay(int year,int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        // 月份是从0开始du计算，所以需要减去1
        c.set(Calendar.MONTH,month-1);
        // 当月最后一天的日期dao
        int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 开始日期为1号
        int start =1;
        // 计数
        int count = 0;
        while(start <= max){
            c.set(Calendar.DAY_OF_MONTH,start);
            if(isWorkDay(c)){
                count++;
            }
            start++;
        }
        return count;
    }
    // 判断是否工作日（未排除法定节假日，由于涉及到农历节日，处理很麻烦）
    public static boolean isWorkDay(Calendar c){
        // 获取星期,1~7,其中1代表星期日，2代表星期一 ... 7代表星期六
        int week = c.get(Calendar.DAY_OF_WEEK);
        // 不是周六和周日的都认为是工作日
        return week != Calendar.SUNDAY  && week != Calendar.SATURDAY;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH,1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Date start = calendar.getTime();
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH,1);
        calendar.set(Calendar.DAY_OF_MONTH,8);
        Date end = calendar.getTime();
        System.out.println(countWorkDays(start,end));
    }
}
