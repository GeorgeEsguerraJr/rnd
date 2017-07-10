package com.sunlinei.cms.batch.jobs.edpimport.service;

import com.sunlinei.cms.batch.db.entities.VisaInc;
import com.sunlinei.cms.batch.jobs.edpimport.model.VisaIncomingFile;

public interface BatchImportService {

	/**
	 * Given the url it returns the SyndFeed built with rome api
	 * 
	 * @param url
	 * @return
	 */
	
	public void transformVisaInc(VisaInc dbInc, VisaIncomingFile inc, int sequence);
}
