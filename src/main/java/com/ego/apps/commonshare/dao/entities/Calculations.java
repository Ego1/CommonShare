package com.ego.apps.commonshare.dao.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "calculations")
@NamedQueries(
	//	{ @NamedQuery(name = "GET_LAST_CALCULATION_ID", query = "SELECT MAX(p.id) FROM Calculations c JOIN c.userGroup g JOIN c.lastPurchase p WHERE g.name = :groupName") })
	{ @NamedQuery(name = "GET_LAST_CALCULATION_ID", query = "SELECT MAX(c.lastPurchase.id) FROM Calculations c JOIN c.userGroup g WHERE g.name = :groupName") })
public class Calculations
	{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "description")
	private String description;

	@Column(name = "calculation_date")
	private Date calculationDate;

	@Column(name = "amount_paid")
	private String amountPaid;

	@Column(name = "amount_share")
	private String amountShare;

	@ManyToOne
	@JoinColumn(name = "user_group", referencedColumnName = "id")
	private Group userGroup;

	@OneToOne
	@JoinColumn(name = "last_purchase_id", referencedColumnName = "id")
	private Purchase lastPurchase;

	/* ******************************** Getters and Setters ********************************** */
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

	public Date getCalculationDate()
		{
		return calculationDate;
		}

	public void setCalculationDate(Date calculationDate)
		{
		this.calculationDate = calculationDate;
		}

	public Group getUserGroup()
		{
		return userGroup;
		}

	public void setUserGroup(Group userGroup)
		{
		this.userGroup = userGroup;
		}

	public Purchase getLastPurchase()
		{
		return lastPurchase;
		}

	public void setLastPurchase(Purchase lastPurchase)
		{
		this.lastPurchase = lastPurchase;
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

	}
