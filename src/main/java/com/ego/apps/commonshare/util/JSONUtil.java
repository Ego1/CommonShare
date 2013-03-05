package com.ego.apps.commonshare.util;

import java.util.Date;

import com.ego.apps.commonshare.dao.entities.Calculation;

public class JSONUtil
	{
	
	static class JSONConstructor
	{
	private StringBuilder builder = new StringBuilder();
	public void begin()
		{
		builder.append("{");
		}
	
	public void end()
		{
		builder.replace(builder.length()-1, builder.length(), "");
		builder.append("}");
		}
	
	public void addOneMoreObject()
		{
		builder.append(",");
		}

	public void add(String key, String value)
		{
		builder.append("\"" + key + "\": \"" + value + "\",");
		}
	
	public String toString()
		{
		return builder.toString();
		}
	}
	

	public static String getCalculationJSON(Calculation calculation)
		{
		JSONConstructor constructor = new JSONConstructor();
		constructor.begin();
		constructor.add("id", Integer.toString(calculation.getId()));
		constructor.add("description", calculation.getDescription());
		constructor.add("calculationdate", DateUtils.getDateFormat_dd_MMM(calculation.getCalculationDate()));
		constructor.add("amountpaid", calculation.getAmountPaid());
		constructor.add("amountshare", calculation.getAmountShare());
		constructor.end();
		return constructor.toString();
		}
	
	public static void main(String args[])
		{
		Calculation  calculation = new Calculation();
		calculation.setId(1);
		calculation.setDescription("Harsh");
		calculation.setCalculationDate(new Date());
		calculation.setAmountPaid("Amount Paid");
		calculation.setAmountShare("Amount Share");
		System.out.println(getCalculationJSON(calculation));
		}
	
	
	}
