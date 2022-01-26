package com.juan.estevez.app.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "APPOINTMENT")
public class Appointment implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_APPOINTMENT")
	private int idAppointment;
	
	@Column(name = "DOCTOR")
	private String doctor;
	
	@Column(name = "PATIENT")
	private String patient;
	
	@Column(name = "FECHA")
	private String date;
	
	@Column(name = "HOUR")
	private int hour;	

}
