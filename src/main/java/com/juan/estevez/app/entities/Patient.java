package com.juan.estevez.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PATIENT")
public class Patient {

	private static final long serialVersionUID = 1L;

	@Id
	private String idPatient;
	@Column
	private String name;
	@Column
	private String dateOfBirth;
	@Column
	private String idType;
	@Column
	private String eps;
	@Column
	private String clinicHistory;

	public Patient() {
	}

	public Patient(String idPatient, String name, String dateOfBirth, String idType, String eps, String clinicHistory) {
		this.idPatient = idPatient;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.idType = idType;
		this.eps = eps;
		this.clinicHistory = clinicHistory;
	}

	public String getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public String getClinicHistory() {
		return clinicHistory;
	}

	public void setClinicHistory(String clinicHistory) {
		this.clinicHistory = clinicHistory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
