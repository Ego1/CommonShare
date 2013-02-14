package com.ego.apps.commonshare.dao.entities.comparators;

import java.util.Comparator;

import com.ego.apps.commonshare.dao.entities.Item;
import com.ego.apps.commonshare.dao.entities.Taxonomy;

/**
 * Compares two Item objects based on the taxonomy hierarchy.
 * 
 * @author sony
 * 
 */
public class ItemComparator implements Comparator<Item>
	{

	class TaxonomyHierarchy
		{
		Taxonomy level1 = null, level2 = null, level3 = null;
		}

	public int compare(Item item1, Item item2)
		{
		// Initialize the item taxonomy level variables.
		TaxonomyHierarchy hierarchy1 = calculateTaxonomyLevels(item1);
		TaxonomyHierarchy hierarchy2 = calculateTaxonomyLevels(item2);

		// We start comparing taxonomies from first level
		int level1Comparison = compareHierarchy(1, hierarchy1, hierarchy2);
		if (level1Comparison != 0)
			{
			return level1Comparison;
			}
		else
			{
			// The level1 comparison is same. We proceed to level2
			int level2Comparison = compareHierarchy(2, hierarchy1, hierarchy2);
			if (level2Comparison != 0)
				{
				return level2Comparison;
				}
			else
				{
				// The level2 comparison is same. We proceed to level2.
				int level3Comparison = compareHierarchy(2, hierarchy1, hierarchy2);
				if (level3Comparison != 0)
					{
					return level3Comparison;
					}
				else
					{
					// Even level3 turns out to be same. So we return 0.
					return 0;
					}
				}
			}
		}

	/**
	 * Compares two taxonomies at a given level and returns a comparison accordingly.
	 * 
	 * @param level
	 *            The level at which comparison is to be made. It should be either 1, 2 or 3. Anything other than these
	 *            would return 0.
	 * @param hierarchy1
	 *            The hierarchy 1.
	 * @param hierarchy2
	 *            The hierarchy 2.
	 * @return negative value indicates that hierarchy1 is lesser than hierarchy2 at this level. positive indicates that
	 *         hierarchy1 is greater then hierarchy2 at this level. A 0 would indicate that they are same at this level.
	 */
	private int compareHierarchy(int level, TaxonomyHierarchy hierarchy1, TaxonomyHierarchy hierarchy2)
		{
		Taxonomy item1Taxonomy = null, item2Taxonomy = null;
		if (level == 1)
			{
			item1Taxonomy = hierarchy1.level1;
			item2Taxonomy = hierarchy2.level1;
			}
		else if (level == 2)
			{
			item1Taxonomy = hierarchy1.level2;
			item2Taxonomy = hierarchy2.level2;
			}
		else if (level == 3)
			{
			item1Taxonomy = hierarchy1.level3;
			item2Taxonomy = hierarchy2.level3;
			}
		else
			{
			// Invalid level. So we return 0, indicating that the reaults are equal.
			return 0;
			}

		if (item1Taxonomy == null && item2Taxonomy == null)
			{
			// Both the item taxonomies are null. This means they are equal.
			return 0;
			}
		else if (item1Taxonomy == null)
			{
			// The item2 is not null and hence greater. So we return a negative value indicating that
			// item1 is smaller than item2.
			return -1;
			}
		else if (item2Taxonomy == null)
			{
			// Item1 is not null and hence greater. So we return positive value indicating that
			// item1 is greater than item2.
			return 1;
			}
		else
			{
			// No one is null. So we need to make a comparison.
			return item1Taxonomy.getName().compareTo(item2Taxonomy.getName());
			}
		}

	/**
	 * Calculates and initialized the item's taxonomy levels based on the what we can obtain from item.
	 * 
	 * @param item
	 *            The item's whose taxonomies at various levels need to be recognised.
	 * @return A TaxonomyHierarchy inner class instance.
	 */
	private TaxonomyHierarchy calculateTaxonomyLevels(Item item)
		{
		TaxonomyHierarchy hierarchy = new TaxonomyHierarchy();
		// Check the level of the item taxonomy
		if (item.getTaxonomy().getParent() == null)
			{
			// Since there is no parent for the item's taxonomy, the item taxonomy is at level 1.
			hierarchy.level1 = item.getTaxonomy();
			}
		else if (item.getTaxonomy().getParent().getParent() == null)
			{
			// Since there is no grand parent for item's taxonomy, the item taxonomy is at level 2 and its parent is at level 1.
			if (item.getTaxonomy().getParent().getParent() == null)
				{
				hierarchy.level2 = item.getTaxonomy();
				hierarchy.level1 = item.getTaxonomy().getParent();
				}
			}
		else
			{
			// Since there is no grand parent for item's taxonomy, the item taxonomy is at level 2 and its parent is at level 1.
			hierarchy.level3 = item.getTaxonomy();
			hierarchy.level2 = item.getTaxonomy().getParent();
			hierarchy.level1 = item.getTaxonomy().getParent().getParent();
			}
		return hierarchy;
		}

	}
