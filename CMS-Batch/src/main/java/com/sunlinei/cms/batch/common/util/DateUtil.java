package com.sunlinei.cms.batch.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Logger;

/**
		
 * **/

public class DateUtil {
   
    static private Logger log = Logger.getLogger(DateUtil.class.getCanonicalName());
    public final static String DATE_FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public final static String DATE_FORMAT_yyyyMMdd = "yyyyMMdd";
    public final static String DATE_FORMAT_ddMMyyyy = "ddMMyyyy";
    public final static String DATE_FORMAT_yyMM = "yyMM";
    public final static String DATE_FORMAT_MMyy = "MMyy";
    public final static String TIME_FORMAT_HHmmss= "HHmmss";
    public final static String DATE_FORMAT_DISPLAY_yyyyMMdd = "yyyy/MM/dd";
    public final static String DATE_FORMAT_DISPLAY_ddMMyyyy = "dd/MM/yyyy";
    public final static String TIME_FORMAT_DISPLAY_HHmm= "HH:mm";
    public final static String TIME_FORMAT_DISPLAY_HHmmss= "HH:mm:ss";
    public final static String DATE_FORMAT_DISPLAY_ddMMyyyyHHmmss = "dd/MM/yyyy HH:mm:ss";
    public final static String DATE_FORMAT_DISPLAY_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    // SAC20131011 [S]
    public final static String DATE_FORMAT_MMddHHmmss = "MMddHHmmss";    
    // SAC20131011 [E]
    
    
    /**    
     * this method returns java.util.Date object for a specific pattern.     
     * sample usage:    
     * String currentDate = "03/03/2003";     
     * Date theDateObject = DateUtil.getDateForString(currentDate, "dd/MM/yyyy");
     * @param date
     * @param pattern
     * @return the Date object for the format specified, returns null if there's problem formatting the date  
     */

    public static Date getDateForString(String date, String pattern) {
        Date result = null;
        try {
        	if(date != null && !date.trim().equals("")){
	            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	            sdf.setLenient(false);
	            result = sdf.parse(date);
        	}
        } catch (Exception ex) {
//        	log.warning("DateUtil:getDateForString: Problem formatting date :"+LogUtil.strStackTrace(ex));
            result = null;
        }
        return result;
    }
    
    public static Date getDateForString(Date date, String pattern) {
        Date result = null;
        try {
        	if(date != null){
        		result = getDateForString(getStringForDate(date,pattern),pattern);
        	}
        } catch (Exception ex) {
//        	log.warning("DateUtil:getDateForString: Problem formatting date :"+LogUtil.strStackTrace(ex));
            result = null;
        }
        return result;
    }

    /**
     * this method returns a String witht the pattern specified for the string object
     * sample usage:
     * Date theCurrentDate = new Date();
     * String formattedDate = DateUtil.getSringForDate(theCurrentDate, "dd/MM/yyyy");
     * @param theDate
     * @param pattern
     * @return the String representation for the format specified. returns null if there's problem formatting the date    
     */
    public static String getStringForDate(Date theDate, String pattern) {
        String result = null;
        try {
        	if(theDate != null){
	            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	            result = sdf.format(theDate);
        	}
        } catch (Exception ex) {
//            log.warning("DateUtil:getStringForDate: Problem formatting date :"+LogUtil.strStackTrace(ex));
            result = null;
        }
        return result;
    }
    
    public static String getStringForDate(String theDate, String oriPattern, String displayPattern) {
        String result = null;
        try {
        	if(theDate != null && !theDate.trim().equals("")){
	            SimpleDateFormat sdf = new SimpleDateFormat(displayPattern);
	            result = sdf.format(getDateForString(theDate,oriPattern));
        	}
        } catch (Exception ex) {
//            log.warning("DateUtil:getStringForDate: Problem formatting date :"+LogUtil.strStackTrace(ex));
            result = null;
        }
        return result;
    }

    public static String addDays(String currentDate, String increaseDays, String datePattern) {
        if (currentDate == null || currentDate.trim().equals("")) {
        	throw new IllegalArgumentException("date is not valid currentDate : " + currentDate);
        }

        if (increaseDays == null || increaseDays.trim().equals("")) {
            increaseDays = "0";
        }

        Date baseDate = getDateForString(currentDate, datePattern);
        
        int dayToIncrease = Integer.parseInt(increaseDays);

        Calendar cal = new GregorianCalendar();
        cal.setTime(baseDate);
        cal.add(Calendar.DAY_OF_MONTH, dayToIncrease);

        Date theNewDate = cal.getTime();       

        return getStringForDate(theNewDate, datePattern);
    }
   
    @SuppressWarnings("deprecation")
	public static long diffDate(Date date1, Date date2) {
        long days_diff = 0;

        if (date1 == null || date2 == null || date2.getYear() > 1100)
                return 0;

        double difference_D = new GregorianCalendar(date1.getYear(), date1.getMonth(), date1.getDate()).getTime().getTime()
                - new GregorianCalendar(date2.getYear(), date2.getMonth(), date2.getDate()).getTime().getTime();

        days_diff = Math.round((difference_D / (1000 * 60 * 60 * 24)));

        return days_diff;
    }
    
	public static String getDisplayDate(Date date, String strFormat) {
		String result = null;
		if(date != null){
			DateFormat dateFormat = new SimpleDateFormat(strFormat);		
			result = dateFormat.format(date);
		}
		return result;
	}
	
	public static String getDisplayTime(Date date, String strFormat) {
		String result = null;
		if(date != null){
			DateFormat dateFormat = new SimpleDateFormat(strFormat);
			return dateFormat.format(date);
		}
		return result;
	}
	
	/**
	 * this method returns a duration of millisecond in HH:mm:ss format
     * sample example:
     * long durationMillisecond = 100000;
     * String formattedDuration = 00:01:40
     * @param long durationMillisecond
     * @return the String representation for the duration formatted. returns null if there's problem formatting the duration
	 * **/
	public static String getDisplayDuration(long durationMillisecond, boolean appendColon){
		String formattedDuration = null;
		String colon = "";
		try{
			if(durationMillisecond < 0){
				return "";
			}
			long time = durationMillisecond / 1000;   
			String seconds = Integer.toString((int)(time % 60));   
			String minutes = Integer.toString((int)((time % 3600) / 60));   
			String hours = Integer.toString((int)(time / 3600));   
			
			for (int i = 0; i < 2; i++) {   
				if (seconds.length() < 2) {
					seconds = "0" + seconds;
				}
				if (minutes.length() < 2) {
					minutes = "0" + minutes;
				}
				if (hours.length() < 2) {
					hours = "0" + hours;
				}   
			}  
			
			if(appendColon){
				colon = ":";
			}
			formattedDuration = hours + colon + minutes + colon + seconds;
		}catch(Exception ex){
//			log.warning("DateUtil:getDisplayDuration:Error occurred."+LogUtil.strStackTrace(ex));
		}
		return formattedDuration;
	}
    
    public static long diffDateInMilliSeconds(Date endDate, Date startDate) {
        long days_diff = -1;
        
        if (endDate == null || startDate == null){
        	return days_diff;
        }

        days_diff = (endDate.getTime() - startDate.getTime());
        
        return days_diff;
    }
    
//    public static String getJulianDateYDDDHH(Date theDate) {
//		JulianDay jd = new JulianDay(theDate.getTime());		
//		int hh = jd.get(JulianDay.HOUR_OF_DAY);		
//		String jHour = MsgFormatter.getPadNumber(2, hh);		
//		return getJulianDateYDDD(theDate) + jHour;
//	}
//    
//    public static String getJulianDateYDDD(Date theDate) {
//    	JulianDay jd = new JulianDay(theDate.getTime());
//		int year = jd.get(JulianDay.YEAR);
//		String theYear = String.valueOf(year);
//		char jYear = theYear.charAt(theYear.length() - 1);
//		return jYear + getJulianDateDDD(theDate);
//	}
    
//    public static String getJulianDateDDD(Date theDate) {
////    	JulianDay jd = new JulianDay(theDate.getTime());	
////		int dayOfYear = jd.get(JulianDay.DAY_OF_YEAR);
//    	Calendar cal = new GregorianCalendar();
//        cal.setTime(theDate);
//		String jDayOfYear = MsgFormatter.getPadNumber(3, cal.get(Calendar.DAY_OF_YEAR));
//		return jDayOfYear;
//	}
    
    public static String addMonths(String currentDate, String increaseMonths, String datePattern) {
    	if (currentDate == null || currentDate.trim().equals("")) {
    		throw new IllegalArgumentException("date is not valid currentDate : " + currentDate);
    	}

    	if (increaseMonths == null || increaseMonths.trim().equals("")) {
        	increaseMonths = "0";
        }
    	
        Date baseDate = getDateForString(currentDate, datePattern);
        int monthToIncrease = Integer.parseInt(increaseMonths);
        System.out.println("current date : " + baseDate);
        
        Calendar cal = new GregorianCalendar();
        cal.setTime(baseDate);
        cal.add(Calendar.MONTH, monthToIncrease);
        Date theNewDate = cal.getTime();
        System.out.println("new date : " + theNewDate);
        
        return getStringForDate(theNewDate, datePattern);
    }
    
	public static String get1stDayOfNextMonth(String currentDate, String datePattern) {		
		Date baseDate = getDateForString(currentDate, datePattern);
		Calendar now = Calendar.getInstance();
		now.setTime(baseDate);
		String firstDateOfNextMonth = null;				
		int mth = now.get(Calendar.MONTH) + 1;		
		int year = now.get(Calendar.YEAR);
		int nextMonth = mth + 1;
		if (mth == 12) {
		  nextMonth = 1;
		  year++;
		}
		firstDateOfNextMonth = year + StringUtil.getPadLeft(String.valueOf(nextMonth), 2, '0')+ "01"; 
	    return firstDateOfNextMonth;			
	}

	public static String getLastDayOfNextMonth(String currentDate, String datePattern) {
		String lastDayOfNextMonth=get1stDayOfNextMonth(currentDate, datePattern);
		lastDayOfNextMonth = getLastDayOfCurrMonth(lastDayOfNextMonth, datePattern);
		return lastDayOfNextMonth;
	}
	
	public static String getLastDayOfCurrMonth(String currentDate, String datePattern) {
		String lastDateOfCurrMonth = get1stDayOfNextMonth(currentDate, datePattern);
		lastDateOfCurrMonth = DateUtil.addDays(lastDateOfCurrMonth, "-1", 
				DateUtil.DATE_FORMAT_yyyyMMdd);
		return lastDateOfCurrMonth;
	}

    public static String getSystemDate(String datePattern) {
        Date theCurrentDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);
        String formattedDate = df.format(theCurrentDate);
        return formattedDate;
    }

    public static String getSystemTime(String datePattern) {
        Date theCurrentDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);
        String formattedTime = df.format(theCurrentDate);
        return formattedTime;
    }
    
    public static long getExceededTimeInMillis(int periodType, int thePeriod) {
		Calendar cal = Calendar.getInstance();		
		cal.setTime(new Date());                        // Set it in the Calendar object
		cal.add(periodType, (thePeriod * -1));
//		System.out.println("The calculated time in millisecond is : " + cal.getTime() + " (" + cal.getTimeInMillis() + ")");
		return cal.getTimeInMillis();
	}
    
    public static String getPassDate(Date theDate, String datePattern, int periodType, int thePeriod){
    	if(theDate == null){
    		return null;
    	}
    	Calendar cal = Calendar.getInstance();		
		cal.setTime(theDate);                        // Set it in the Calendar object
		cal.add(periodType, (thePeriod * -1));
		return DateUtil.getStringForDate(cal.getTime(), datePattern);
    }
    
    /** AMBB20101311 [S] **/
    public static String getFormattedGMTDateTime(Date date, String pattern) {
		DateFormat df = createDateFormat(pattern);
		return df.format(date);
	}
    
    private static DateFormat createDateFormat(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		TimeZone gmt = TimeZone.getTimeZone("GMT");
		sdf.setTimeZone(gmt);
		sdf.setLenient(true);
		return sdf;
	}
    
    public static void main(String[] arg){
//    	Date testDate = DateUtil.getDateForString("2009041", DateUtil.DATE_FORMAT_yyyyMMdd);
//    	System.out.println("Pass date :"+getPassDate(DateUtil.getDateForString("20090529093229",DateUtil.DATE_FORMAT_yyyyMMddHHmmss), DateUtil.DATE_FORMAT_yyyyMMddHHmmss, Calendar.MONTH, 6));
//    	System.out.println("Pass date :"+getPassDate(new Date(), DateUtil.DATE_FORMAT_yyyyMMddHHmmss, Calendar.MONTH, 6));
    	System.out.println("new Date:"+new Date());
    	System.out.println("Date :"+DateUtil.getStringForDate(new Date(), "yyyyMMdd HH:mm:ss"));
    	System.out.println("GMT Date:"+getFormattedGMTDateTime(new Date(), "yyyyMMdd HH:mm:ss"));
    }
    
    /** AMBB20101311 [E] **/
}
