package com.ego.apps.commonshare.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class BaseDAO
	{
	private static EntityManagerFactory emf;
	protected EntityManager entityManager;

	protected Logger logger = Logger.getLogger(getClass());

	static
		{
		emf = Persistence.createEntityManagerFactory("CommonSharePersistenceUnit");
		}

	/**
	 * Instantiate DAO with a new entity manager.
	 */
	public BaseDAO()
		{
		entityManager = emf.createEntityManager();
		}

	/**
	 * Instantiate DAO and use the entity manager passed.
	 * 
	 * @param entityManager
	 */
	public BaseDAO(EntityManager entityManager)
		{
		this.entityManager = entityManager;
		}

	/**
	 * Closes all the resources related to this DAO class.
	 */
	public void close()
		{
		if (entityManager != null)
			{
			entityManager.close();
			}
		}
	}
