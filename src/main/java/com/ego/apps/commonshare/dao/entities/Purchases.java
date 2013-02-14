package com.ego.apps.commonshare.dao.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="purchases")
public class Purchases
	{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="item")
	private String item;
	
	@Column(name="date")
	private Date purchaseDate;
	
	@Column(name="paidBy")
	private String paidBy;
	
	@Column(name="excluded_persons")
	private String excludedPersons;

	public int getId()
		{
		return id;
		}

	public void setId(int id)
		{
		this.id = id;
		}

	public String getItem()
		{
		return item;
		}

	public void setItem(String item)
		{
		this.item = item;
		}

	public Date getPurchaseDate()
		{
		return purchaseDate;
		}

	public void setPurchaseDate(Date purchaseDate)
		{
		this.purchaseDate = purchaseDate;
		}

	public String getPaidBy()
		{
		return paidBy;
		}

	public void setPaidBy(String paidBy)
		{
		this.paidBy = paidBy;
		}

	public String getExcludedPersons()
		{
		return excludedPersons;
		}

	public void setExcludedPersons(String excludedPersons)
		{
		this.excludedPersons = excludedPersons;
		}
	}
