package com.trimhelp.starter.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @author wq
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static final SimpleDateFormat SF = new SimpleDateFormat();

    /**
     * 日期格式为yyyy-MM-dd HH:mm
     */
    private static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 日期格式为yyyy-MM-dd HH:mm
     */
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 日期格式为yyyy-MM-dd
     */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 指定日期多少秒之后
     *
     * @param date
     * @param second
     * @return
     */
    public static Date addSec(Date date, int second) {
        Calendar now = Calendar.getInstance();
        date.toInstant();
        now.setTime(date);
        now.add(Calendar.SECOND, second);
        return now.getTime();
    }

    /**
     * 指定日期多少分钟之后
     *
     * @param date
     * @param min
     * @return
     */
    public static Date addMin(Date date, int min) {
        Calendar now = Calendar.getInstance();
        date.toInstant();
        now.setTime(date);
        now.add(Calendar.MINUTE, min);
        return now.getTime();
    }

    /**
     * 指定日期多少小时之后
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar now = Calendar.getInstance();
        date.toInstant();
        now.setTime(date);
        now.add(Calendar.HOUR, hour);
        return now.getTime();
    }

    /**
     * 指定日期多少天之后
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addDay(Date date, int hour) {
        Calendar now = Calendar.getInstance();
        date.toInstant();
        now.setTime(date);
        now.add(Calendar.DATE, hour);
        return now.getTime();
    }

    /**
     * 时间戳
     *
     * @return
     */
    public static String getTimeChuo() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sf.format(new Date());
    }

    /**
     * 获取起始时间和结束时间
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Map getStartAnEndTime(Date startDate, Date endDate) {
        Map<String, Date> map = new HashMap<>();
        map.put("startDate", getStartTime(startDate));
        map.put("endDate", getEndTime(endDate));
        return map;
    }

    /**
     * 获取给定月份的起始时间
     *
     * @param startDate
     * @return
     */
    public static Date getStartTime(Date startDate) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(startDate);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return getStartDate(cale);
    }


    /**
     * 获取给定月份的结束时间
     *
     * @param endDate
     * @return
     */
    public static Date getEndTime(Date endDate) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(endDate);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return getEndDate(cale);
    }


    /**
     * 获取指定日期时期时间 例 2019 05 12 00 : 00 : 000
     *
     * @param cale
     */
    public static Date getStartDate(Calendar cale) {
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        cale.set(Calendar.MILLISECOND, 0);
        return cale.getTime();
    }

    /**
     * 获取指定日期的结束时间 例 2019 05 12 23 : 59 : 599
     *
     * @param cale
     */
    public static Date getEndDate(Calendar cale) {
        cale.set(Calendar.HOUR_OF_DAY, 23);
        cale.set(Calendar.MINUTE, 59);
        cale.set(Calendar.SECOND, 59);
        cale.set(Calendar.MILLISECOND, 599);
        return cale.getTime();
    }

    /**
     * 获取两个月份之间总天数
     *
     * @param startDate 起始月份 例 "201905"
     * @param endDate   结束月份 例 "201906"
     * @return
     */
    public static Integer getTotalDays(Date startDate, Date endDate) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(startDate);
        int start = instance.get(Calendar.DAY_OF_YEAR);
        instance.setTime(endDate);

        int end = instance.get(Calendar.DAY_OF_YEAR);

        return end - start + 1;
    }

    /**
     * 获取转换好的结束时间字符串
     *
     * @param enddate    结束时间字符串
     * @param dateFormat 起始的时间字符串格式
     * @param format     转换的时间字符串格式
     * @return
     * @throws ParseException
     */
    public static String getEnddateStringByFormart(String enddate, SimpleDateFormat dateFormat, SimpleDateFormat format) throws ParseException {
        if (ValidateUtil.isOneEmpty(enddate)) {
            //取当天时间结束
            Calendar cale = Calendar.getInstance();
            cale.setTime(new Date());
            Date end = DateUtil.getEndDate(cale);
            enddate = format.format(end);
        } else {
            Calendar cale = Calendar.getInstance();
            cale.setTime(dateFormat.parse(enddate));
            Date end = DateUtil.getEndDate(cale);
            enddate = format.format(end);
        }
        return enddate;
    }

    /**
     * 获取转换好的起始时间字符串
     *
     * @param startdate  起始时间字符串
     * @param dateFormat 起始的时间字符串格式
     * @param format     转换的时间字符串格式
     * @return
     * @throws ParseException
     */
    public static String getStartdateStringByFormart(String startdate, SimpleDateFormat dateFormat, SimpleDateFormat format) throws ParseException {
        if (null == startdate || "".equals(startdate)) {
            //取当天时间起始
            Calendar cale = Calendar.getInstance();
            cale.setTime(new Date());
            Date start = DateUtil.getStartDate(cale);
            startdate = format.format(start);
        } else {
            Calendar cale = Calendar.getInstance();
            cale.setTime(dateFormat.parse(startdate));
            Date start = DateUtil.getStartDate(cale);
            startdate = format.format(start);
        }
        return startdate;
    }

    /**
     * 格式化时间（字符串）
     *
     * @param date
     * @param pattern
     * @return
     */
    public synchronized static String getDateTimeStr(Date date, String pattern) {
        if (!StringUtils.isNotEmpty(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(date);
    }

    /**
     * 格式化时间（Date）
     *
     * @param date
     * @param pattern
     * @return
     */
    public synchronized static Date getDateTime(String date, String pattern) {
        if (!StringUtils.isNotEmpty(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        try {
            return sf.parse(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取指定日期的节假日信息
     * 正常工作日对应结果为 0, 法定节假日对应结果为 1, 节假日调休补班对应的结果为 2，休息日对应结果为 3
     * 节假日数据说明：本接口包含2017年起的中国法定节假日数据，数据来源国务院发布的公告，每年更新1次，确保数据最新
     * 示例：http://api.goseek.cn/Tools/holiday?date=20170528
     *
     * @param day
     * @return
     */
    public static int getWorkDayTag(String day) {
        String result = HttpUtil.sendPost("http://api.goseek.cn/Tools/holiday", "date=" + day);
        return JSON.parseObject(result).getInteger("data");
    }

    /**
     * 从map中获取起始时间和结束时间(格式yyyy-MM-dd)改为格式为(yyyy-MM-dd HH:mm:ss),最后将值重新赋值到map中
     *
     * @param map
     * @throws ParseException
     */
    public static void handleBeginAndEndTime(Map<String, Object> map) throws ParseException {
        //时间参数处理
        boolean isBeginTime = StringUtils.isNotBlank((String) map.get("beginTime"));
        boolean isEndTime = StringUtils.isNotBlank((String) map.get("endTime"));
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (isBeginTime) {
            Date beginDate = format.parse((String) map.get("beginTime"));
            instance.setTime(beginDate);
            Date startDate = DateUtil.getStartDate(instance);
            String beginTime = dateFormat.format(startDate);
            map.put("beginTime", beginTime);
        }
        if (isEndTime) {
            Date endDate = format.parse((String) map.get("endTime"));
            instance.setTime(endDate);
            Date end = DateUtil.getEndDate(instance);
            String endTime = dateFormat.format(end);
            map.put("endTime", endTime);
        }
    }

    /**
     * 获取过去7天内的日期数组
     *
     * @param intervals intervals天内
     * @return 日期数组
     */
    public static ArrayList<String> getDays(int intervals) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = intervals - 1; i >= 0; i--) {
            pastDaysList.add(getPastDate(i));
        }
        return pastDaysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }


    /**
     * 获取本月当前时间段
     * 例如:今日2019-07-09 19:19:02
     * 获取起始时间为2019-07-01 00:00:00
     * 结束时间为2019-07-09 23:59:59
     */
    public static Map<String, String> getCurrentMonthTime() {
        //当前时间
        Date currentTime = new Date();
        //获取本月的起始时间
        Date startTime = DateUtil.getStartTime(currentTime);
        //时间格式转换
        String startDate = DateUtil.getDateTimeStr(startTime, "yyyy-MM-dd HH:mm:ss");
        String endDate = DateUtil.getDateTimeStr(currentTime, "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beginTime = null;
        String endTime = null;
        try {
            beginTime = DateUtil.getStartdateStringByFormart(startDate, dateFormat, dateFormat);
            endTime = DateUtil.getEnddateStringByFormart(endDate, dateFormat, dateFormat);
        } catch (ParseException e) {
            logger.error("cn.stylefeng.guns.core.util.DateUtil.getCurrentMonthTime",e.getMessage());
            e.printStackTrace();
        }
        Map<String, String> result = new HashMap<>();
        result.put("beginTime", beginTime);
        result.put("endTime", endTime);
        return result;
    }

    //获取本年的开始时间
    public static Date getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return getDayStartTime(cal.getTime());
    }

    //获取本年的结束时间
    public static Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());

    }

    //获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    //获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }


    /**
     * 当前季度的开始时间
     */
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3){
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 4 && currentMonth <= 6){
                c.set(Calendar.MONTH, 3);
            } else if (currentMonth >= 7 && currentMonth <= 9){
                c.set(Calendar.MONTH, 6);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 9);
            }
            c.set(Calendar.DATE, 1);
            now = datetimeFormat.parse(dateFormat.format(c.getTime()) + " 00:00");
        } catch (Exception e) {
            logger.error("当前季度的开始时间",e.getMessage());
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = simpleDateFormat.parse(dateFormat.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            logger.error("当前季度的结束时间",e.getMessage());
            e.printStackTrace();
        }
        return now;
    }

    public static List<String> getCurrentQuarterMonths() {
        List<String> monthBetween = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        int month = instance.get(Calendar.MONTH) + 1;
        if (month >= 1 && month <= 3) {
            monthBetween.add("1");
            monthBetween.add("2");
            monthBetween.add("3");
            return monthBetween;
        } else if (month >= 4 && month <= 6) {
            monthBetween.add("4");
            monthBetween.add("5");
            monthBetween.add("6");
            return monthBetween;
        } else if (month >= 7 && month <= 9) {
            monthBetween.add("7");
            monthBetween.add("8");
            monthBetween.add("9");
            return monthBetween;
        } else if (month >= 10 && month <= 12) {
            monthBetween.add("10");
            monthBetween.add("11");
            monthBetween.add("12");
            return monthBetween;
        }
		/* Date currentQuarterStartTime = getCurrentQuarterStartTime();
        Date currentQuarterEndTime = getCurrentQuarterEndTime();
        String minDate = getDateTimeStr(currentQuarterStartTime, "yyyy-MM-dd HH:mm:ss");
        String maxDate = getDateTimeStr(currentQuarterEndTime, "yyyy-MM-dd HH:mm:ss");

        try {
            monthBetween = getMonthBetween(minDate, maxDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        return monthBetween;
    }

    private static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * 获取当前月份是第几季度
     */
    public static Integer getCurrentQuarterCount() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        if (currentMonth >= 1 && currentMonth <= 3) {
            return 1;
        } else if (currentMonth >= 4 && currentMonth <= 6) {
            return 2;
        } else if (currentMonth >= 7 && currentMonth <= 9) {
            return 3;
        } else if (currentMonth >= 10 && currentMonth <= 12) {
            return 4;
        }

        return null;
    }

    /**
     * 获取月份(0：当前月份；1：上一个月份；2：下一月份)
     */
    public static Integer getMonthByType(int type) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
        String month = df.format(LocalDateTime.now());
        YearMonth yearMonth = YearMonth.parse(month);
        int result = yearMonth.getMonthValue();
        if (type == 1) {
            result = yearMonth.minusMonths(1).getMonthValue();
        } else if (type == 2) {
            result = yearMonth.plusMonths(1).getMonthValue();
        }
        return result;
    }

    /**
     * 获取年份(0：当前年份；1：上一年份；2：下一年份)
     */
    public static Integer getYearByType(int type) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
        String month = df.format(LocalDateTime.now());
        YearMonth yearMonth = YearMonth.parse(month);
        int result = yearMonth.getYear();
        if (type == 1) {
            result = yearMonth.minusYears(1).getYear();
        } else if (type == 2) {
            result = yearMonth.plusYears(1).getYear();
        }
        return result;
    }

    /**
     * 获取月份的季度数
     */
    public static Integer getQuarterCountByMonth(int month) {
        if (month >= 1 && month <= 3) {
            return 1;
        } else if (month >= 4 && month <= 6) {
            return 2;
        } else if (month >= 7 && month <= 9) {
            return 3;
        } else if (month >= 10 && month <= 12) {
            return 4;
        }
        return null;
    }

    /**
     * 获取一月对应日期数（0：获取当天；1：当月最后一天）
     *
     * @param type
     * @return
     */
    public static Integer getDayOfMonth(int type) {
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();
        if (type == 1) {
            LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
            day = lastDay.getDayOfMonth();
        }
        return day;
    }

    /**
     * 通过日期获取本年的第几周
     */
    public static Integer getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        return weekOfYear;
    }
    /**
     * 获取本周(中国是周一到周日)所在年份（主要是周数跨年的问题）
     */
    public static String getYearOfWeek() {
        String week = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("YYYY-w"));
        String yearCount = week.split("-")[0];
        return yearCount;
    }


    /**
     * 获取本周的时间端
     */
    public static Map<String,Object> getWeekBeginTimeAndEndTime(Date date) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //TODO 获取当前时间
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate inputDate = instant.atZone(zoneId).toLocalDate();
        //LocalDate inputDate = LocalDate.now();
        //TODO 本周开始时间
        TemporalAdjuster FIRST_OF_WEEK =
                TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue()));
        String weekStart = df.format(inputDate.with(FIRST_OF_WEEK));
        try {
            weekStart = getStartdateStringByFormart(weekStart, dateFormat, simpleDateFormat);
        } catch (ParseException e) {
            logger.error("cn.stylefeng.guns.core.util.DateUtil.getWeekBeginTimeAndEndTime(java.util.Date)",e.getMessage());
            e.printStackTrace();
        }

        //TODO 本周结束时间
        TemporalAdjuster LAST_OF_WEEK =
                TemporalAdjusters.ofDateAdjuster(localDate -> localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));
        String weekEnd = df.format(inputDate.with(LAST_OF_WEEK));
        try {
            weekEnd = getEnddateStringByFormart(weekEnd, dateFormat, simpleDateFormat);
        } catch (ParseException e) {
            logger.error("cn.stylefeng.guns.core.util.DateUtil.getWeekBeginTimeAndEndTime(java.util.Date)",e.getMessage());
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("beginTime", weekStart);
        map.put("endTime", weekEnd);
        return map;
    }
    /**
     * 获取某年中第几周的起止日期时间(每周从周一开始（使用ISO8601标准）)
     * 例如2018-W02-1(2018年第2周的周一)，2018-W48-7(2018年第48周的周日)
     */
    public static Map<String,Object> getWeekBeginTimeAndEndTime(int year,int week) {
        String weekStart=year+"-W"+String.format("%02d",week)+"-1";
        String weekEnd=year+"-W"+String.format("%02d",week)+"-7";
        LocalDate lStart=LocalDate.parse(weekStart, DateTimeFormatter.ISO_WEEK_DATE);
        LocalDate lEnd=LocalDate.parse(weekEnd, DateTimeFormatter.ISO_WEEK_DATE);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String beginTime=lStart.format(df)+" 00:00:00";
        String endTime=lEnd.format(df)+" 23:59:59";
        Map<String, Object> map = new HashMap<>();
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        return map;
    }

    /**
     * date格式为yyyy-MM
     * 根据日期获取对应月份的所有时间
     */
    public static List<String> getAllMonthDays(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5));
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, year);
        instance.set(Calendar.MONTH, month - 1);
        instance.set(Calendar.DATE, 1);
        instance.roll(Calendar.DATE, -1);
        int days = instance.get(Calendar.DATE);
        List<String> resultList = new ArrayList<>();
        for (int i = 1; i <= days; i++) {
            String day = null;
            if (i < 10) {
                day = String.valueOf(year) + "-" + String.valueOf(month) + "-0" + String.valueOf(i);
            } else {
                day = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(i);
            }
            resultList.add(day);

        }
        return resultList;
    }

    public static int getWeekOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        return weekOfMonth;
    }

    /**
     * 获取某年中某一月的起止日期时间(type:1格式化到日期；2格式化到时间)
     */
    public static Map<String,Object> getMonthBeginTimeAndEndTime(int year,int month,int type) {
        LocalDate day = LocalDate.now().withYear(year).withMonth(month);
        //某一月的第一天
        LocalDate firstday = LocalDate.of(day.getYear(),day.getMonth(),1);
        //某一月最后一天
        LocalDate lastDay =day.with(TemporalAdjusters.lastDayOfMonth());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String beginTime=firstday.format(df);
        String endTime=lastDay.format(df);
        Map<String, Object> map = new HashMap<>();
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        if(type==2){
            map.put("beginTime",beginTime+" 00:00:00");
            map.put("endTime",endTime+" 23:59:59");
        }

        return map;
    }

    /**
     * 字符串日期转LocalDate
     * @param date
     * @param pattern
     * @return
     */
    public static LocalDate getLocalDate(String date,String pattern){
        DateTimeFormatter format=DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date,format);
    }

    /**
     * 获取某年中某一季度的起止日期时间(type:1格式化到日期；2格式化到时间)
     */
    public static Map<String,Object> getQuarterBeginTimeAndEndTime(int year,int quarter,int type) {
        int startMonth=0;
        int endMonth=0;
        if(quarter==1){
            //第一季度
            startMonth=1;
            endMonth=3;
        }else if(quarter==2){
            //第二季度
            startMonth=4;
            endMonth=6;
        }else if(quarter==3){
            //第三季度
            startMonth=7;
            endMonth=9;
        }else if(quarter==4){
            //第四季度
            startMonth=10;
            endMonth=12;
        }
        LocalDate startDay = LocalDate.now().withYear(year).withMonth(startMonth);
        LocalDate endDay = LocalDate.now().withYear(year).withMonth(endMonth);
        //某季度的第一天
        LocalDate firstday = LocalDate.of(startDay.getYear(),startDay.getMonth(),1);
        //某季度最后一天
        LocalDate lastDay =endDay.with(TemporalAdjusters.lastDayOfMonth());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String beginTime=firstday.format(df);
        String endTime=lastDay.format(df);
        Map<String, Object> map = new HashMap<>();
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        if(type==2){
            map.put("beginTime",beginTime+" 00:00:00");
            map.put("endTime",endTime+" 23:59:59");
        }
        return map;
    }

    /**
     *
     * 获取当前日期   时间格式：yyyy-MM-dd
     * @return
     */
    public static String getCurrentDate(){
        return dateFormat.format(new Date());
    }

    /**
     * 获取本月第一天   时间格式：yyyy-MM-dd
     * @return
     */
    public static String getFirstDayOfMonth(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return dateFormat.format(cal.getTime());
    }

    /**
     * 获取当前日期前一天   时间格式：yyyy-MM-dd
     *
     * @return
     */
    public static String getYesterday() {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //前一天时间
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        String date = sdf.format(calendar.getTime());
        return date;
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 时间戳转换为字符串
     * @param time
     * @return
     */
    public static String getDateToString(long time){
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    /**
     * 直接获取时间戳
     * @return
     */
    public static String getTimeStamp(){
        Date date = new Date();
        return String.valueOf(date.getTime());
    }



}
