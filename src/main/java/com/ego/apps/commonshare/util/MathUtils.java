package com.ego.apps.commonshare.util;

import java.util.Random;

public class MathUtils
	{
	private static Random random = new Random();

	public static int getRandomNumber()
		{
		return random.nextInt();
		}

	public static String get2DecimalTruncatedString(Double number)
		{
		String strNumber = number.toString();
		return get2DecimalTruncatedString(strNumber);
		}

	public static String get2DecimalTruncatedString(String strNumber)
		{
		if (strNumber.length() > strNumber.indexOf(".") + 3)
			{
			strNumber = strNumber.substring(0, (strNumber.indexOf(".") + 3));
			}
		return strNumber;
		}
	}
