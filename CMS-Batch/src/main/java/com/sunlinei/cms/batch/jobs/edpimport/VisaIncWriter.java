package com.sunlinei.cms.batch.jobs.edpimport;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.sunlinei.cms.batch.db.entities.VisaInc;
import com.sunlinei.cms.batch.jobs.edpimport.dao.ImportDao;
import com.sunlinei.cms.batch.jobs.edpimport.model.VisaIncomingFile;
import com.sunlinei.cms.batch.jobs.edpimport.service.BatchImportService;

public class VisaIncWriter implements ItemWriter<VisaIncomingFile>{

	@Autowired
	ImportDao importDao;
	
	@Autowired
	BatchImportService batchImportService;
	
	@Override
	public void write(List<? extends VisaIncomingFile> items) throws Exception {
		int sequence = 0;
		for(VisaIncomingFile v : items) {
			VisaInc visaDbRecord = new VisaInc();
			//Translate 
			batchImportService.transformVisaInc(visaDbRecord, v, sequence);
			//Persist
			importDao.addVisaIncRec(visaDbRecord);
			sequence++;
		}

	}

}
