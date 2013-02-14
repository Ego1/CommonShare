package com.ego.apps.commonshare.actions.vo;

public class TaxonomyVO
	{
	private int id;
	private String name;
	private String description;
	private int parent;

	public int getId()
		{
		return id;
		}

	public void setId(int id)
		{
		this.id = id;
		}

	public void setId(String id)
		{
		this.id = Integer.parseInt(id);
		}

	public String getName()
		{
		return name;
		}

	public void setName(String name)
		{
		this.name = name;
		}

	public String getDescription()
		{
		return description;
		}

	public void setDescription(String description)
		{
		this.description = description;
		}

	public int getParent()
		{
		return parent;
		}

	public void setParent(int parent)
		{
		this.parent = parent;
		}

	/*public void setParent(String parent)
		{
		this.parent = Integer.parseInt(parent);
		}
*/
	}
