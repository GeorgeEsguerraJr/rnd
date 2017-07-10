package com.sunlinei.cms.batch.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cardbrand database table.
 * 
 */
@Entity
@NamedQuery(name="CardBrand.findAll", query="SELECT c FROM CardBrand c")
public class CardBrand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cardbrand_id")
	private String cardbrandId;

	@Column(name="cardbrand_description")
	private String cardbrandDescription;

	private String checksum;

	//bi-directional many-to-one association to ImportFile
	@OneToMany(mappedBy="cardbrand")
	private List<ImportFile> importfiles;

	public CardBrand() {
	}

	public String getCardbrandId() {
		return this.cardbrandId;
	}

	public void setCardbrandId(String cardbrandId) {
		this.cardbrandId = cardbrandId;
	}

	public String getCardbrandDescription() {
		return this.cardbrandDescription;
	}

	public void setCardbrandDescription(String cardbrandDescription) {
		this.cardbrandDescription = cardbrandDescription;
	}

	public String getChecksum() {
		return this.checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public List<ImportFile> getImportfiles() {
		return this.importfiles;
	}

	public void setImportfiles(List<ImportFile> importfiles) {
		this.importfiles = importfiles;
	}

	public ImportFile addImportfile(ImportFile importfile) {
		getImportfiles().add(importfile);
		importfile.setCardbrand(this);

		return importfile;
	}

	public ImportFile removeImportfile(ImportFile importfile) {
		getImportfiles().remove(importfile);
		importfile.setCardbrand(null);

		return importfile;
	}

}