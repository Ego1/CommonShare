package com.ego.apps.commonshare.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ego.apps.commonshare.exceptions.CSBusinessException;

public class DateUtils
	{
	private static DateFormat format_dd_mm_yyyy = new SimpleDateFormat("dd/mm/yyyy");
	private static DateFormat format_dd_month = new SimpleDateFormat("dd MMM");

	public static Date getDateFromFormat_dd_mm_yyyy(String date) throws CSBusinessException
		{
		try
			{
			return format_dd_mm_yyyy.parse(date);
			}
		catch (ParseException parseException)
			{
			throw new CSBusinessException(parseException);
			}
		}
	
	public static String getDateFormat_dd_MMM(Date date)
		{
		return format_dd_month.format(date);
		}
	
	public static void main(String args[])
		{
		Date date = new Date();
		System.out.println(getDateFormat_dd_MMM(date));
		}
	}
