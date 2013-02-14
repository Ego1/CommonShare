package com.ego.apps.commonshare.dao.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class Purchase
	{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item", referencedColumnName = "id")
	private Item item;

	@Column(name = "purchase_date")
	private Date purchaseDate;

	@Column(name = "paid_by")
	private String paidBy;

	@Column(name = "excluded_persons")
	private String excludedPersons;

	@Column(name = "comment")
	private String comment;

	@ManyToOne
	@JoinColumn(name = "usergroup", referencedColumnName = "id")
	private Group userGroup;

	/* *************************** Getters and Setters ***************************** */
	public int getId()
		{
		return id;
		}

	public void setId(int id)
		{
		this.id = id;
		}

	public Item getItem()
		{
		return item;
		}

	public void setItem(Item item)
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

	public String getComment()
		{
		return comment;
		}

	public void setComment(String comment)
		{
		this.comment = comment;
		}

	public Group getUserGroup()
		{
		return userGroup;
		}

	public void setUserGroup(Group userGroup)
		{
		this.userGroup = userGroup;
		}

	}
