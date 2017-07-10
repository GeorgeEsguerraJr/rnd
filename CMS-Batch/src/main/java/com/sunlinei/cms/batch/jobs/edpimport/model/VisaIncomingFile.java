package com.sunlinei.cms.batch.jobs.edpimport.model;

public class VisaIncomingFile {

	private String visaTransactionCode;
	private String filler1;
	private String transactionSequence;
	private String filler2;
	private String reportingGroup;
	private String correlationReportId;
	private String filler3;
	
	public VisaIncomingFile() {
		super();
	}
	//Decoding TODO: transfer to properties file
	public VisaIncomingFile(String data) {
//		if(data.length() != 168) return;
		System.out.println("VisaIncomingFile:"+data.length());
		System.out.println("VisaIncomingFile:"+data);
		visaTransactionCode = data.substring(0, 2);
		filler1 = data.substring(2, 3);
		transactionSequence = data.substring(3, 4);
		filler2 = data.substring(4,58);
		reportingGroup = data.substring(58,59);
		correlationReportId = data.substring(59,61);
		filler3 = data.substring(61);
	}
	
//	public void loadTo(VisaInc rec) {
//		rec.setTrxnCd(StringUtil.chkNullBigNumber(visaTransactionCode));
//		rec.setFiller1(filler1);
//		rec.setTrxnSeq(StringUtil.chkNullBigNumber(transactionSequence));
//		rec.setFiller2(filler2);
//		rec.setRptGrp(reportingGroup);
//		rec.setCorpRptId(correlationReportId);
//		rec.setFiller3(filler3);
//	}
	
	public String getVisaTransactionCode() {
		return visaTransactionCode;
	}
	public void setVisaTransactionCode(String visaTransactionCode) {
		this.visaTransactionCode = visaTransactionCode;
	}
	public String getFiller1() {
		return filler1;
	}
	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}
	public String getTransactionSequence() {
		return transactionSequence;
	}
	public void setTransactionSequence(String transactionSequence) {
		this.transactionSequence = transactionSequence;
	}
	public String getFiller2() {
		return filler2;
	}
	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}
	public String getReportingGroup() {
		return reportingGroup;
	}
	public void setReportingGroup(String reportingGroup) {
		this.reportingGroup = reportingGroup;
	}
	public String getCorrelationReportId() {
		return correlationReportId;
	}
	public void setCorrelationReportId(String correlationReportId) {
		this.correlationReportId = correlationReportId;
	}
	public String getFiller3() {
		return filler3;
	}
	public void setFiller3(String filler3) {
		this.filler3 = filler3;
	}
	
	@Override
	public String toString() {
		return "VisaIncomingFile [visaTransactionCode=" + visaTransactionCode + ", filler1=" + filler1
				+ ", transactionSequence=" + transactionSequence + ", filler2=" + filler2 + ", reportingGroup="
				+ reportingGroup + ", correlationReportId=" + correlationReportId + ", filler3=" + filler3 + "]";
	}
	

}
