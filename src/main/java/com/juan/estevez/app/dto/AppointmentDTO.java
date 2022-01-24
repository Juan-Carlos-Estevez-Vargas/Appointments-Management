package com.juan.estevez.app.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class AppointmentDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idAppointment;
	private String doctor;
	private String patient;
	private int hour;
	
}
