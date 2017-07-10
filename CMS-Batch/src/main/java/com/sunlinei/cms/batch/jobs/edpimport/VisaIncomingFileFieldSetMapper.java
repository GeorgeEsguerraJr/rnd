package com.sunlinei.cms.batch.jobs.edpimport;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.sunlinei.cms.batch.jobs.edpimport.model.VisaIncomingFile;

public class VisaIncomingFileFieldSetMapper implements FieldSetMapper<VisaIncomingFile> {

	@Override
	public VisaIncomingFile mapFieldSet(FieldSet fieldSet) throws BindException {
		String readString = fieldSet.readString("ALL");
		System.out.println("###READSTRING####"+readString);
		VisaIncomingFile visaIncomingFile = new VisaIncomingFile(readString);

		return visaIncomingFile;
	}
	

}
