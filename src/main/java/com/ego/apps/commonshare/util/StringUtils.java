package com.ego.apps.commonshare.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtils
	{
	private StringUtils()
		{

		}

	/**
	 * Checks if the input string is empty of not. It first checks for null. Then trims the string and checks it's
	 * size
	 * is not 0.
	 * 
	 * @param string
	 *            The string to be checked.
	 * @return true if the string is empty. false otherwise.
	 */
	public static boolean isEmpty(String string)
		{
		if (string == null || string.trim().length() == 0)
			{
			return true;
			}
		return false;
		}

	/**
	 * Breaks a string into colon and semicolon based pattern.
	 * e.g. a:b;c:d;e:f will be broken down into map
	 * a -- b, c -- d, e -- f
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, String> breakColonAndSemicolonSeparatedString(String str)
		{
		Map<String, String> returnMap = new HashMap<String, String>();
		String[] semicolonSeparatedArr = str.split(";");
		for (int semicolonSeparatedCtr = 0; semicolonSeparatedCtr < semicolonSeparatedArr.length; semicolonSeparatedCtr++)
			{
			if (!isEmpty(semicolonSeparatedArr[semicolonSeparatedCtr]))
				{
				String[] colonSeparated = semicolonSeparatedArr[semicolonSeparatedCtr].split(":");
				returnMap.put(colonSeparated[0], colonSeparated[1]);
				}
			}
		return returnMap;
		}

	/**
	 * Adds all the elements of array into one string. All the elemets will be separated by the mentioned separator.<br/>
	 * e.g. if the array is <br/>
	 * a b c d e<br/>
	 * and the separator is ;<br/>
	 * Then the resultant string will be a;b;c;d;e
	 * 
	 * @param arr
	 *            The input array of strings.
	 * @param separator
	 *            The separator to be used.
	 * @return The separator separated string of input elements.
	 */
	public static String arrayToSeparatedString(String[] arr, String separator)
		{
		StringBuffer retStringBuf = new StringBuffer();

		if (arr != null)
			{
			if (arr.length == 1)
				{
				retStringBuf.append(arr[0]);
				return retStringBuf.toString();
				}
			else
				{
				for (int arrCtr = 0; arrCtr < arr.length; arrCtr++)
					{
					retStringBuf.append(arr[arrCtr]);
					retStringBuf.append(separator);
					}
				return retStringBuf.subSequence(0, (retStringBuf.length() - separator.length())).toString();
				}
			}
		return null;
		}

	/**
	 * Converts a map of <String,Double> to a map of <String,String> by truncating double value to 2 decimal places.
	 * 
	 * @param map
	 *            Input <String, Double> map.
	 * @return Output <String, String> map.
	 */
	public static Map<String, String> doubleMapToStringMap(Map<String, Double> map)
		{
		Map<String, String> returnMap = new HashMap<String, String>();
		for (String key : map.keySet())
			{
			String value = map.get(key).toString();
			if (!isEmpty(value))
				{
				value = MathUtils.get2DecimalTruncatedString(value);
				}
			returnMap.put(key, value.toString());
			}
		return returnMap;
		}

	/**
	 * Changes a map to a string. The map <br/>
	 * a -- b, <br/>
	 * c -- d, <br/>
	 * e -- f <br/>
	 * With ':' as separator1 and ';' as separator2<br/>
	 * is changed to a:b;c:d;e:f
	 * 
	 * @param map
	 *            The map to be converted into string.
	 * @param separator1
	 *            The string to be used as a key-value separator.
	 * @param separator2
	 *            The string to be used as map element separator.
	 * @return The return string.
	 */
	public static String mapToSeparatedString(Map<String, ? extends Object> map, String separator1, String separator2)
		{
		StringBuffer strBuff = new StringBuffer();
		for (String key : map.keySet())
			{
			String value = map.get(key).toString();
			strBuff.append(key);
			strBuff.append(separator1);
			strBuff.append(value);
			strBuff.append(separator2);
			}
		return strBuff.subSequence(0, strBuff.length() - separator2.length()).toString();
		}
	}
