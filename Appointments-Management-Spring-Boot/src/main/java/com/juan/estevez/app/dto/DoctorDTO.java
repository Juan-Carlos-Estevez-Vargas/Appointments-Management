package com.juan.estevez.app.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class DoctorDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String idDoctor;
	private String doctorsName;
	private String idType;
	private String numberProfessionalCard;
	private int yearsExperience;
	private String specialty;
	private double attentionStartTime;
	private double attentionEndTime;
	
}
