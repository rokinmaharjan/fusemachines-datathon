package com.attendance.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static Date convertStringToDateTime(String dateTimeInString) {
		Date dateTime = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			dateTime = formatter.parse(dateTimeInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
}
