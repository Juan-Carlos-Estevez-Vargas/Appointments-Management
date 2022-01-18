package com.juan.estevez.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
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

}
