package com.juan.estevez.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APPOINTMENT")
public class Appointment {

	private static final long serialVersionUID = 1L;

	@Id
	private int idAppointment;
	@Column
	private String doctor;
	@Column
	private String patient;
	@Column
	private String hour;

	public Appointment() {

	}

	public Appointment(int idAppointment, String doctor, String patient, String hour) {
		super();
		this.idAppointment = idAppointment;
		this.doctor = doctor;
		this.patient = patient;
		this.hour = hour;
	}

	public int getIdAppointment() {
		return idAppointment;
	}

	public void setIdAppointment(int idAppointment) {
		this.idAppointment = idAppointment;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
