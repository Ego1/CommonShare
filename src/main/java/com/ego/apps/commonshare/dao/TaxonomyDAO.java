package com.ego.apps.commonshare.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ego.apps.commonshare.actions.vo.TaxonomyVO;
import com.ego.apps.commonshare.dao.entities.Group;
import com.ego.apps.commonshare.dao.entities.Taxonomy;
import com.ego.apps.commonshare.exceptions.CSBusinessException;

public class TaxonomyDAO extends BaseDAO
	{
	public TaxonomyDAO()
		{
		super();
		}
	
	public TaxonomyDAO(EntityManager entityManager)
		{
		super(entityManager);
		}
	/**
	 * This method must be invoked only by the cache class that holds group specific information. Invoking it from
	 * outside leads to processing wastage.
	 * 
	 * @param groupName
	 *            The name of group for which the information is required.
	 * @return A list of taxonomy objects for the given group.
	 * 
	 */
	public List<Taxonomy> getTaxonomies(String groupName)
		{
		Query query = entityManager.createNamedQuery("GET_ALL_TAXONOMIES");
		query.setParameter("groupName", groupName);
		List<Taxonomy> taxonomies = query.getResultList();
		return taxonomies;
		}

	public Taxonomy addTaxonomy(TaxonomyVO taxonomyVO, String groupName) throws CSBusinessException
		{
		/*
		 * Check if the taxonomy exists with the given group. If it exists, return null. Else create a taxonomy with the
		 * given parent (check if parent exists) and return the information.
		 */
		// Begin transaction
		entityManager.getTransaction().begin();
		GroupDAO groupDAO = new GroupDAO(entityManager);
		Group group = groupDAO.getGroup(groupName);
		Query query = entityManager.createNamedQuery("GET_TAXONOMY_BY_NAME_AND_GROUP");
		query.setParameter("name", taxonomyVO.getName());
		query.setParameter("groupName", groupName);
		List<Taxonomy> taxonomies = query.getResultList();
		if (!(taxonomies == null || taxonomies.size() == 0))
			{
			// The taxonomy already exists.
			entityManager.getTransaction().rollback();
			throw new CSBusinessException("taxonomymanagement.error.taxonomy-already-exists");
			}
		else
			{
			Taxonomy parent = null;
			Taxonomy taxonomy = new Taxonomy();
			taxonomy.setName(taxonomyVO.getName());
			taxonomy.setDescription(taxonomyVO.getDescription());
			// Check if the parent is mentioned.
			if (taxonomyVO.getParent() == 0)
				{
				// There is no parent.
				}
			else
				{
				// Check if the parent exists.
				query = entityManager.createNamedQuery("GET_TAXONOMY_BY_ID_AND_GROUP");
				query.setParameter("id", taxonomyVO.getParent());
				query.setParameter("groupName", groupName);
				taxonomies = query.getResultList();
				if (taxonomies == null || taxonomies.size() == 0)
					{
					entityManager.getTransaction().rollback();
					throw new CSBusinessException("taxonomymanagement.error.taxonomy-parent-doesnot-exist");
					}
				else
					{
					parent = taxonomies.get(0);
					taxonomy.setParent(parent);
					}
				}
			taxonomy.setUserGroup(group);
			
			// The mentioned parent exists. We need to create this new taxonomy now.
			entityManager.persist(taxonomy);
			entityManager.getTransaction().commit();
			return taxonomy;
			}
		}

	public Taxonomy getTaxonomyById(int id)
		{
		return entityManager.find(Taxonomy.class, id);
		}
	}