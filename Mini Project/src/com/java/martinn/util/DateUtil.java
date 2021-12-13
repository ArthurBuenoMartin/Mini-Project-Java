package com.java.martinn.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date convertToDate(String date) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date toDate = null;
		try {
			toDate = df.parse(date);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Wrong date format");
		}
		
		return toDate;
	}
	
	
}
