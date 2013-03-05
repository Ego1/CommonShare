package com.ego.apps.commonshare.actions.vo;

import java.util.List;

import com.ego.apps.commonshare.dao.entities.Calculation;
import com.ego.apps.commonshare.dao.entities.Purchase;

/**
 * Class to bind calculation together with its purchases.
 * 
 * @author sony
 * 
 */
public class CalculationVO
	{
	private Calculation calculation;
	private List<Purchase> purchases;

	public Calculation getCalculation()
		{
		return calculation;
		}

	public void setCalculation(Calculation calculation)
		{
		this.calculation = calculation;
		}

	public List<Purchase> getPurchases()
		{
		return purchases;
		}

	public void setPurchases(List<Purchase> purchases)
		{
		this.purchases = purchases;
		}

	}
