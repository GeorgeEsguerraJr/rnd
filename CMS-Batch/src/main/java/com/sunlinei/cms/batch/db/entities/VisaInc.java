package com.sunlinei.cms.batch.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the visainc database table.
 * 
 */
@Entity
@NamedQuery(name="VisaInc.findAll", query="SELECT v FROM VisaInc v")
public class VisaInc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="seq_no")
	private Long seqNo;

	@Column(name="cd_qfy")
	private BigDecimal cdQfy;

	private String checksum;

	@Column(name="corp_rpt_id")
	private String corpRptId;

	private String filler1;

	private String filler2;

	private String filler3;

	@Column(name="proc_date")
	private String procDate;

	@Column(name="rpt_grp")
	private String rptGrp;

	@Column(name="trxn_cd")
	private BigDecimal trxnCd;

	@Column(name="trxn_seq")
	private BigDecimal trxnSeq;

	private String usage;

	public VisaInc() {
	}

	public Long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public BigDecimal getCdQfy() {
		return this.cdQfy;
	}

	public void setCdQfy(BigDecimal cdQfy) {
		this.cdQfy = cdQfy;
	}

	public String getChecksum() {
		return this.checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getCorpRptId() {
		return this.corpRptId;
	}

	public void setCorpRptId(String corpRptId) {
		this.corpRptId = corpRptId;
	}

	public String getFiller1() {
		return this.filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}

	public String getFiller2() {
		return this.filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}

	public String getFiller3() {
		return this.filler3;
	}

	public void setFiller3(String filler3) {
		this.filler3 = filler3;
	}

	public String getProcDate() {
		return this.procDate;
	}

	public void setProcDate(String procDate) {
		this.procDate = procDate;
	}

	public String getRptGrp() {
		return this.rptGrp;
	}

	public void setRptGrp(String rptGrp) {
		this.rptGrp = rptGrp;
	}

	public BigDecimal getTrxnCd() {
		return this.trxnCd;
	}

	public void setTrxnCd(BigDecimal trxnCd) {
		this.trxnCd = trxnCd;
	}

	public BigDecimal getTrxnSeq() {
		return this.trxnSeq;
	}

	public void setTrxnSeq(BigDecimal trxnSeq) {
		this.trxnSeq = trxnSeq;
	}

	public String getUsage() {
		return this.usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

}