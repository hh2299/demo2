package com.example.demo.common.util;



import com.example.demo.common.vo.PeriodBO;
import com.example.demo.common.vo.UnitPeriodBO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: DaleShay
 * @Date: 2021/6/16 18:39
 * @Description:
 */
public class SplitPeriodUtil {

    /**
     * 根据计费单位的 月份数计算 当前 开始、结束 中有多少个单位周期  多余月份 多余天
     * @param startDate
     * @param endDate
     * @param dayOfMonth
     * @param interval
     * @return
     */
    public static UnitPeriodBO calcUnitPeriod(Date startDate, Date endDate, Integer dayOfMonth, Integer interval) {

        List <PeriodBO> periodList = SplitPeriodUtil.splitPeriod(startDate,endDate,dayOfMonth,interval);

        Integer periodCount = 0;
        Integer monthCount = 0;
        Integer dayCount = 0;


        for(PeriodBO period :periodList) {
            Boolean isFull = period.getIsFull();
            if(isFull) {
                periodCount++;
            }else{
                if(interval > 1) {
                    //如果未满周期 且周期大于1个月  继续根据月计算周期 => 得到结果 => 满周期则累加月份数 不满周期则计算天数
                    //此方式拆分为月租金优先
                    List <PeriodBO> subList = SplitPeriodUtil.splitPeriod(period.getStartDate(),period.getEndDate(),dayOfMonth,1);
                    for(PeriodBO sub:subList) {
                        if(sub.getIsFull()) {
                            monthCount++;
                        }else{
                            dayCount += DateRangeUtil.dayBetween(sub.getStartDate(),sub.getEndDate());
                        }
                    }
                }else{
                    dayCount += DateRangeUtil.dayBetween(period.getStartDate(),period.getEndDate());
                }

            }
        }
//        // System.out.println("单位周期数"   +"   "+"多余月份"+"   "+"多余天数");
//        // System.out.println(periodCount+"     "+monthCount+"      "+dayCount);
        return new UnitPeriodBO(periodCount,monthCount,dayCount);
    }

    /**
     * 根据开始和结束时间 拆分为多个周期
     * @param feeStartDate 开始时间
     * @param feeEndDate 结束时间
     * @param dayOfMonth 截取天数 自然月为 1   租赁月为 合同开始日
     * @param interval 间隔月数
     * @return
     */
    public static List<PeriodBO> splitPeriod(Date feeStartDate, Date feeEndDate, Integer dayOfMonth, Integer interval) {
        List <PeriodBO> result = new ArrayList <>();
        splitPeriod(result, feeStartDate, feeEndDate, dayOfMonth, interval);
        return result;
    }


    private static void splitPeriod(List <PeriodBO> result, Date fromDate, Date feeEndDate, Integer dayOfMonth, Integer interval) {

        if (DateRangeUtil.isAfter(fromDate, feeEndDate)) {
            return;
        }

        Calendar from = Calendar.getInstance();
        from.setTime(fromDate);

        Integer fromDayOfMonth = from.get(Calendar.DAY_OF_MONTH);


        //定义周期结束日期 通过 开始日期 和周期计算
        Calendar to = Calendar.getInstance();
        to.setTime(fromDate);

        Boolean isFull = false;
        Integer compare = fromDayOfMonth.compareTo(dayOfMonth);
        if (compare >= 0) {
            //如果 开始日期天 在 周期日之后 则加上周期
            to.set(Calendar.MONTH, to.get(Calendar.MONTH) + interval);
            //设置 to 日期为 周期日 前一天。
            to.set(Calendar.DAY_OF_MONTH, dayOfMonth - 1);
            if (compare == 0) {
                //如果刚好相等 则有可能是满周期
                isFull = true;
            }
        }else{
            //设置 to 日期为 周期日 前一天。
            to.set(Calendar.DAY_OF_MONTH, dayOfMonth - 1);
        }
        Date toDate = to.getTime();

        if (DateRangeUtil.isBefore(feeEndDate, toDate)) {
            //如果 费项结束日期 在 计算得到的周期结束日期 之前 则是提前结束周期 不为满周期
            toDate = feeEndDate;
            isFull = false;
        }
        //添加当前周期数据
        PeriodBO vo = new PeriodBO();
        vo.setStartDate(fromDate);
        vo.setEndDate(toDate);
        vo.setIsFull(isFull);
        result.add(vo);

        //将 to+1 天作为开始时间获取下一个周期
        Integer day = to.get(Calendar.DAY_OF_MONTH) + 1;
        to.set(Calendar.DAY_OF_MONTH, day);
        splitPeriod(result, to.getTime(), feeEndDate, dayOfMonth, interval);
    }

    public static void main(String[] args) {
        Date startTime = DateUtil.parse("2021-01-07 23:32:11", "yyyy-MM-dd HH:mm:ss");
        Date endTime = DateUtil.parse("2021-01-31 23:32:11", "yyyy-MM-dd HH:mm:ss");


        List <PeriodBO> result  =splitPeriod(startTime, endTime, 1, 1);

        for (PeriodBO vo : result) {
            // System.out.println(DateUtil.formatDate(vo.getStartDate()) + " " + DateUtil.formatDate(vo.getEndDate())  + " "+ vo.getIsFull());
        }
    }
}
