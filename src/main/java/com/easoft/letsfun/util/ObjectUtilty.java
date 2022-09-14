package com.easoft.letsfun.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ObjectUtilty {

	private static final SimpleDateFormat DF_withNotSecondAndMillisecond = new SimpleDateFormat("ddMMyyyyhhmm");
	
	private static final SimpleDateFormat DF_ddMMyyyyhhmmss = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

	public static final int SECOND = 0;
	public static final int MINUTE = 1;
	public static final int HOUR = 2;

	public static boolean isEmpty(Object object) {
		
		if (object == null) {
			return true;
		}
		if (object instanceof String) {
			
			String strObj = object.toString();
			return strObj.trim().length() == 0;
			
		} 
		else if (object instanceof List<?>) {
			
			List<?> listObj = (List<?>) object;
			return listObj.size() == 0;
		} 
		
		else if (object instanceof Map<?, ?>) {
			
			Map<?, ?> mapObj = (Map<?, ?>) object;
			return mapObj.size() == 0;
			
		}
		
		return false;
	}

	public static Date createNextDate(int time, int increment) {
		
		Calendar calendar = Calendar.getInstance();
		
		switch (time) {
		case SECOND:
			calendar.add(Calendar.SECOND, increment);
			break;
		case MINUTE:
			calendar.add(Calendar.MINUTE, increment);
			break;
		case HOUR:
			calendar.add(Calendar.HOUR, increment);
			break;
		default:
			break;
		}
		
		return calendar.getTime();
	}

	public static boolean compareDate_ddMMyyyyhhmm(Date date1, Date date2) {
		
		String d1 = DF_withNotSecondAndMillisecond.format(date1);
		String d2 = DF_withNotSecondAndMillisecond.format(date2);
		
		if (d1.equals(d2)) {
			return true;
		}
		
		return false;
	}

	public static String dateToString_ddMMyyyyhhmmss(Date date) {
		
		String formatDate = null;
		
		if (date != null) {
			formatDate = DF_ddMMyyyyhhmmss.format(date);
		}
		
		return formatDate;
	}
	

	


}
