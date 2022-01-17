package com.juan.estevez.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "DOCTOR")
public class Doctor {

	private static final long serialVersionUID = 1L;

	@Id
	private String idDoctor;
	@Column
	private String doctorsName;
	@Column
	private String idType;
	@Column
	private String numberProfessionalCard;
	@Column
	private double yearsExperience;
	@Column
	private String specialty;
	@Column
	private String attentionStartTime;
	@Column
	private String attentionEndTime;

	public Doctor() {
	}

	public Doctor(String idDoctor, String doctorsName, String idType, String numberProfessionalCard,
			double yearsExperience, String specialty, String attentionStartTime, String attentionEndTime) {
		this.idDoctor = idDoctor;
		this.doctorsName = doctorsName;
		this.idType = idType;
		this.numberProfessionalCard = numberProfessionalCard;
		this.yearsExperience = yearsExperience;
		this.specialty = specialty;
		this.attentionStartTime = attentionStartTime;
		this.attentionEndTime = attentionEndTime;
	}

	public String getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getDoctorsName() {
		return doctorsName;
	}

	public void setDoctorsName(String doctorsName) {
		this.doctorsName = doctorsName;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getNumberProfessionalCard() {
		return numberProfessionalCard;
	}

	public void setNumberProfessionalCard(String numberProfessionalCard) {
		this.numberProfessionalCard = numberProfessionalCard;
	}

	public double getYearsExperience() {
		return yearsExperience;
	}

	public void setYearsExperience(double yearsExperience) {
		this.yearsExperience = yearsExperience;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getAttentionStartTime() {
		return attentionStartTime;
	}

	public void setAttentionStartTime(String attentionStartTime) {
		this.attentionStartTime = attentionStartTime;
	}

	public String getAttentionEndTime() {
		return attentionEndTime;
	}

	public void setAttentionEndTime(String attentionEndTime) {
		this.attentionEndTime = attentionEndTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
