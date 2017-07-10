package com.sunlinei.cms.batch.jobs.edpimport;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sunlinei.cms.batch.jobs.edpimport.dao.ImportDao;
import com.sunlinei.cms.batch.jobs.edpimport.dao.ImportDaoImpl;
import com.sunlinei.cms.batch.jobs.edpimport.service.BatchImportService;
import com.sunlinei.cms.batch.jobs.edpimport.service.BatchImportServiceImpl;

@Configuration
public class ImportServicesConfiguration {

	@Bean
	public ImportDao importDao(){
		return new ImportDaoImpl();
	}
	
	@Bean
	public BatchImportService batchImportService(){
		return new BatchImportServiceImpl();
	}

	
}
