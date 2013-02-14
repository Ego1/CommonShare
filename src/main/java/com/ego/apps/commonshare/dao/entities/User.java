package com.ego.apps.commonshare.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.ego.apps.commonshare.enumerations.Role;

@Entity
@Table(name = "USER")
@NamedQueries(
	{ @NamedQuery(name = "GET_USER_BY_LOGIN", query = "SELECT u FROM User u WHERE u.login=:loginname") })
public class User
	{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "login")
	private String login;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String emailId;

	@Column(name = "password")
	private String password;

	@Column(name = "active")
	private boolean active;

	@Column(name = "deleted")
	private boolean deleted;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "usergroup", referencedColumnName = "id")
	private Group group;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	public int getId()
		{
		return id;
		}

	public void setId(int id)
		{
		this.id = id;
		}

	public String getLogin()
		{
		return login;
		}

	public void setLogin(String login)
		{
		this.login = login;
		}

	public String getName()
		{
		return name;
		}

	public void setName(String name)
		{
		this.name = name;
		}

	public String getEmailId()
		{
		return emailId;
		}

	public void setEmailId(String emailId)
		{
		this.emailId = emailId;
		}

	public String getPassword()
		{
		return password;
		}

	public void setPassword(String password)
		{
		this.password = password;
		}

	public boolean isActive()
		{
		return active;
		}

	public void setActive(boolean active)
		{
		this.active = active;
		}

	public boolean isDeleted()
		{
		return deleted;
		}

	public void setDeleted(boolean deleted)
		{
		this.deleted = deleted;
		}

	public Group getGroup()
		{
		return group;
		}

	public void setGroup(Group group)
		{
		this.group = group;
		}

	public Role getRole()
		{
		return role;
		}

	public void setRole(Role role)
		{
		this.role = role;
		}

	}
