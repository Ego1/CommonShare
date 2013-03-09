package com.ego.apps.commonshare.dao.entities.comparators;

import java.util.Comparator;

import com.ego.apps.commonshare.dao.entities.Item;

/**
 * Compares two Item objects based on the taxonomy hierarchy.
 * 
 * @author sony
 * 
 */
public class ItemComparator implements Comparator<Item>
	{

	public int compare(Item item1, Item item2)
		{
		return item1.getId() - item2.getId();
		}
	}
