package com.ego.apps.commonshare.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author sony
 *
 */
@Entity
@Table(name="item")
@NamedQueries(
		@NamedQuery(name="GET_ALL_GROUP_ITEMS", query="Select i from Item i join i.userGroup g where g.name=:groupName ")
		)
public class Item
	{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "usergroup", referencedColumnName = "id")
	Group userGroup;
	
	@ManyToOne
	@JoinColumn(name = "taxonomy", referencedColumnName = "id")
	private Taxonomy taxonomy;

	public int getId()
		{
		return id;
		}

	public void setId(int id)
		{
		this.id = id;
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

	public Group getUserGroup()
		{
		return userGroup;
		}

	public void setUserGroup(Group userGroup)
		{
		this.userGroup = userGroup;
		}

	public Taxonomy getTaxonomy()
		{
		return taxonomy;
		}

	public void setTaxonomy(Taxonomy taxonomy)
		{
		this.taxonomy = taxonomy;
		}
	
	
	}
