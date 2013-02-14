package com.ego.apps.commonshare.actions.vo;

import com.ego.apps.commonshare.dao.entities.Group;
import com.ego.apps.commonshare.dao.entities.Item;
import com.ego.apps.commonshare.dao.entities.Purchase;
import com.ego.apps.commonshare.exceptions.CSBusinessException;
import com.ego.apps.commonshare.util.DateUtils;

public class PurchaseUIVO
	{
	private String itemname;
	private String itemid;
	private String date;
	private String comment;
	private String excludeFromShare;
	private String paymentSpread;
	
	/* ************************** Utility Methods ************************** */
	public Purchase getPurchase() throws CSBusinessException
		{
		Purchase purchase = new Purchase();
		Item item = new Item();
		try
			{
			item.setId(Integer.parseInt(itemid));
			}
		catch (NumberFormatException numberFormatException)
			{
			throw new CSBusinessException("The mentioned item is incorrect.");
			}
		item.setName(itemname);
		purchase.setItem(item);

		purchase.setPurchaseDate(DateUtils.getDateFromFormat_dd_mm_yyyy(date));
		purchase.setComment(comment);
		purchase.setExcludedPersons(excludeFromShare);
		purchase.setPaidBy(paymentSpread);
		
		return purchase;
		}

	/* ************************** Getters and Setters ************************** */

	public String getItemname()
		{
		return itemname;
		}

	public void setItemname(String itemname)
		{
		this.itemname = itemname;
		}

	public String getItemid()
		{
		return itemid;
		}

	public void setItemid(String itemid)
		{
		this.itemid = itemid;
		}

	public String getDate()
		{
		return date;
		}

	public void setDate(String date)
		{
		this.date = date;
		}

	public String getComment()
		{
		return comment;
		}

	public void setComment(String comment)
		{
		this.comment = comment;
		}

	public String getExcludeFromShare()
		{
		return excludeFromShare;
		}

	public void setExcludeFromShare(String excludeFromShare)
		{
		this.excludeFromShare = excludeFromShare;
		}

	public String getPaymentSpread()
		{
		return paymentSpread;
		}

	public void setPaymentSpread(String paymentSpread)
		{
		this.paymentSpread = paymentSpread;
		}

	}
