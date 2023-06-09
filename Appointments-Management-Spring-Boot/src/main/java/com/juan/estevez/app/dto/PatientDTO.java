package com.juan.estevez.app.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class PatientDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String idPatient;
	private String name;
	private String dateOfBirth;
	private String idType;
	private String eps;
	private String clinicHistory;
	
}
