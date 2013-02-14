package com.ego.apps.commonshare.dao.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "taxonomy")
@NamedQueries(
	{ @NamedQuery(name = "GET_ALL_TAXONOMIES", query = "Select t from Taxonomy t join t.userGroup g where g.name=:groupName"),
	@NamedQuery(name = "GET_TAXONOMY_BY_NAME_AND_GROUP", query = "Select t from Taxonomy t join t.userGroup g where t.name = :name and g.name = :groupName"),
	@NamedQuery(name = "GET_TAXONOMY_BY_ID_AND_GROUP", query = "Select t from Taxonomy t join t.userGroup g where t.id = :id and g.name = :groupName")})
public class Taxonomy
	{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "taxonomy")
	private String name;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "taxonomy")
	private List<Item> items = new ArrayList<Item>();

	@ManyToOne
	@JoinColumn(name = "parent", referencedColumnName = "id")
	private Taxonomy parent;

	@OneToMany(mappedBy = "parent")
	private List<Taxonomy> children;

	@ManyToOne
	@JoinColumn(name = "usergroup", referencedColumnName = "id")
	private Group userGroup;

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

	public List<Item> getItems()
		{
		return items;
		}

	public void setItems(List<Item> items)
		{
		this.items = items;
		}

	public Taxonomy getParent()
		{
		return parent;
		}

	public void setParent(Taxonomy parent)
		{
		this.parent = parent;
		}

	public List<Taxonomy> getChildren()
		{
		return children;
		}

	public void setChildren(List<Taxonomy> children)
		{
		this.children = children;
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
