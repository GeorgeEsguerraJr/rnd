package com.sunlinei.cms.batch.jobs.edpimport.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import com.sunlinei.cms.batch.common.util.DateUtil;
import com.sunlinei.cms.batch.common.util.StringUtil;
import com.sunlinei.cms.batch.db.entities.VisaInc;
import com.sunlinei.cms.batch.jobs.edpimport.model.VisaIncomingFile;

public class BatchImportServiceImpl implements BatchImportService {
	
	private static Logger LOG = Logger.getLogger(BatchImportServiceImpl.class);

	@Override
	public void transformVisaInc(VisaInc dbInc, VisaIncomingFile inc, int sequence) {
		System.out.println("transformVisaInc:"+inc+":"+sequence);
		dbInc.setCdQfy(BigDecimal.ZERO);
		dbInc.setCorpRptId(inc.getCorrelationReportId());
		dbInc.setFiller1(inc.getFiller2());
		dbInc.setFiller2(StringUtil.getPadRight(inc.getFiller3(), 54, ' '));
		dbInc.setFiller3(""); //nothing
		dbInc.setProcDate(DateUtil.getStringForDate(new Date(), DateUtil.DATE_FORMAT_yyyyMMdd));
		dbInc.setRptGrp(inc.getReportingGroup());
		dbInc.setSeqNo(new BigDecimal(inc.getTransactionSequence()).longValue());
		dbInc.setTrxnCd(new BigDecimal(inc.getVisaTransactionCode()));
		dbInc.setTrxnSeq(new BigDecimal(inc.getTransactionSequence()));
		
		System.out.println(dbInc.toString());
	}
}
