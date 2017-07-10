package com.sunlinei.cms.batch.jobs.edpimport.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sunlinei.cms.batch.db.entities.VisaInc;

public class ImportDaoImpl implements ImportDao {

	// @PersistenceContext(unitName = "batchPersistenceUnit")
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addVisaIncRec(VisaInc rec) {
		entityManager.persist(rec);
		entityManager.flush();
	}
}
