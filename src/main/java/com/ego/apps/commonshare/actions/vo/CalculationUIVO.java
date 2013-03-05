package com.ego.apps.commonshare.actions.vo;

import java.util.List;

import com.ego.apps.commonshare.dao.entities.Calculation;
import com.ego.apps.commonshare.util.DateUtils;

/**
 * VO object for calculations. It will be used while displaying the calculation information. It will automatically
 * transform the Calculation object into a UI representable form.
 * 
 * @author sony
 * 
 */
public class CalculationUIVO
	{
	private int id;
	private String description;
	private String calculationDate;
	private String amountPaid;
	private String amountShare;
	private List<PurchaseUIVO> purchases;

	public CalculationUIVO(Calculation calculation)
		{
		this.id = calculation.getId();
		this.description = calculation.getDescription();
		this.calculationDate = DateUtils.getDateFormat_dd_MMM(calculation.getCalculationDate());
		this.amountPaid = calculation.getAmountPaid();
		this.amountShare = calculation.getAmountShare();
		}

	public int getId()
		{
		return id;
		}

	public void setId(int id)
		{
		this.id = id;
		}

	public String getDescription()
		{
		return description;
		}

	public void setDescription(String description)
		{
		this.description = description;
		}

	public String getCalculationDate()
		{
		return calculationDate;
		}

	public void setCalculationDate(String calculationDate)
		{
		this.calculationDate = calculationDate;
		}

	public String getAmountPaid()
		{
		return amountPaid;
		}

	public void setAmountPaid(String amountPaid)
		{
		this.amountPaid = amountPaid;
		}

	public String getAmountShare()
		{
		return amountShare;
		}

	public void setAmountShare(String amountShare)
		{
		this.amountShare = amountShare;
		}

	public List<PurchaseUIVO> getPurchases()
		{
		return purchases;
		}

	public void setPurchases(List<PurchaseUIVO> purchases)
		{
		this.purchases = purchases;
		}

	}
