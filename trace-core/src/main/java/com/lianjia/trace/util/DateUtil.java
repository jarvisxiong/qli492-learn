package com.lianjia.trace.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

	private static final Object object = new Object();
	
	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	
	public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

	public static final String MOMENT_START = "00:00:00";
	
	public static final String MOMENT_END = "23:59:59";

	/**
	 * 获取SimpleDateFormat
	 * 
	 * @param pattern
	 *            日期格式
	 * @return SimpleDateFormat对象
	 * @throws RuntimeException
	 *             异常：非法日期格式
	 */
	public static SimpleDateFormat getDateFormat(String pattern)
			throws RuntimeException {
		SimpleDateFormat dateFormat = threadLocal.get();
		if (dateFormat == null) {
			synchronized (object) {
				if (dateFormat == null) {
					dateFormat = new SimpleDateFormat(pattern);
					dateFormat.setLenient(false);
					threadLocal.set(dateFormat);
				}
			}
		}
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}

//	/**
//	 * 获取日期中的某数值。如获取月份
//	 * 
//	 * @param date
//	 *            日期
//	 * @param dateType
//	 *            日期格式
//	 * @return 数值
//	 */
//	private static int getInteger(Date date, int dateType) {
//		int num = 0;
//		Calendar calendar = Calendar.getInstance();
//
//		if (date != null) {
//			calendar.setTime(date);
//			num = calendar.get(dateType);
//		}
//		return num;
//	}

	/**
	 * getYearMonth(获取年月)
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public static String getYearMonth(Date date) {
		SimpleDateFormat sdf = DateUtil.getDateFormat("yyyyMM");
		return sdf.format(date);
	}
	
	public static Integer getYearMonthInt(Date date) {
		SimpleDateFormat sdf = DateUtil.getDateFormat("yyyyMM");
		return Integer.valueOf(sdf.format(date));
	}
	
	public static String getFormatDate(Date date, String format) {
		SimpleDateFormat sdf = DateUtil.getDateFormat(format);
		return sdf.format(date);
	}

    /**
     * 获取日期时间字符串
     * @param date date
     * @return string like 'yyyy-MM-dd HH:mm:ss'
     */
    private static SimpleDateFormat sdfYMDHMS = DateUtil.getDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getDateString(Date date) {
        return sdfYMDHMS.format(date);
    }
    
    private static SimpleDateFormat sdfYMDHMSSimple = new SimpleDateFormat("yyyyMMddHHmmss");
    public static String getYMDHMS(Date date) {
    	return sdfYMDHMSSimple.format(date);
    }
    
    private static SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyyMMdd");
    public static String getYMDByYMDHMS(String yyyyMMddHHmmss){
    	try {
			return sdfYMD.format(sdfYMDHMSSimple.parse(yyyyMMddHHmmss));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }
    
	/**
	 * getMonth(获得当前月)
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public static int getMonth() {
		Calendar ca = Calendar.getInstance();
		return ca.get(Calendar.MONTH)+1;
	}

	/**
	 * yearMonth2DateTime(根据年月获取时间)
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public static long yearMonth2DateTime(int yearMonth) {
		return yearMonth;
	}

	public static String second2String(int second) {
		int h = 0, d = 0, s = 0;
		s = second % 60;
		second = second / 60;
		d = second % 60;
		h = second / 60;
		return h + "时" + d + "分" + s + "秒";
	}
	
	public static Date getStartDate(Date date) {
		if (date != null) {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(date);

			GregorianCalendar ret = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH));
			return ret.getTime();
		}
		return null;
	}

	public static Date getEndDate(Date date) {
		if (date != null) {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(date);

			GregorianCalendar ret = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH));
			ret.add(Calendar.DAY_OF_MONTH, 1);
			return new Date(ret.getTimeInMillis() - 1);
		}
		return null;
	}
	
	public static boolean isAMOfDay() {
		Calendar c = Calendar.getInstance();
		if (Calendar.AM == c.get(Calendar.AM_PM)) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println(sdfYMDHMS.parse("2015-04-07 00:00:00"));
		System.out.println(sdfYMDHMSSimple.parse("20150407000000"));
	}
}