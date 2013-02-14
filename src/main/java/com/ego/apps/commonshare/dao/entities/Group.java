package com.ego.apps.commonshare.dao.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_group")
@NamedQueries(
	{ @NamedQuery(name = "GET_GROUP", query = "SELECT g FROM Group g WHERE g.name = :name"),
			@NamedQuery(name = "GET_ALL_USERS_IN_GROUP", query = "SELECT g.users FROM Group g WHERE g.name = :groupName") })
public class Group
	{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	// @OneToMany(mappedBy = "group", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<User> users;

	public Group()
		{
		users = new ArrayList<User>();
		}

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

	public List<User> getUsers()
		{
		return users;
		}

	public void setUsers(List<User> users)
		{
		this.users = users;
		}

	}
