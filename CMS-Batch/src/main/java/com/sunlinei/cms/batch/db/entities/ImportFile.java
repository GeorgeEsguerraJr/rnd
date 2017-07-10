package com.sunlinei.cms.batch.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the importfiles database table.
 * 
 */
@Entity
@Table(name="importfiles")
@NamedQuery(name="ImportFile.findAll", query="SELECT i FROM ImportFile i")
public class ImportFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cardbrand_id")
	private Long cardbrandId;

	private String checksum;

	@Column(name="import_filename_prefix")
	private String importFilenamePrefix;

	//bi-directional many-to-one association to CardBrand
	@ManyToOne
	@JoinColumn(name="cardbrand_id", insertable = false, updatable = false)
	private CardBrand cardbrand;

	public ImportFile() {
	}

	public Long getCardbrandId() {
		return this.cardbrandId;
	}

	public void setCardbrandId(Long cardbrandId) {
		this.cardbrandId = cardbrandId;
	}

	public String getChecksum() {
		return this.checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getImportFilenamePrefix() {
		return this.importFilenamePrefix;
	}

	public void setImportFilenamePrefix(String importFilenamePrefix) {
		this.importFilenamePrefix = importFilenamePrefix;
	}

	public CardBrand getCardbrand() {
		return this.cardbrand;
	}

	public void setCardbrand(CardBrand cardbrand) {
		this.cardbrand = cardbrand;
	}

}