package com.juan.estevez.app.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DOCTOR")
public class Doctor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_DOCTOR")
	private String idDoctor;
	
	@Column(name = "DOCTORS_NAME")
	private String doctorsName;
	
	@Column(name = "ID_TYPE")
	private String idType;
	
	@Column(name = "NUMBER_PROFESSIONAL_CARD")
	private String numberProfessionalCard;
	
	@Column(name = "YEARS_EXPERIENCE")
	private int yearsExperience;
	
	@Column(name = "SPECIALTY")
	private String specialty;
	
	@Column(name = "ATTENTION_START_TIME")
	private double attentionStartTime;
	
	@Column(name = "ATTENTION_END_TIME")
	private double attentionEndTime;

}
