package com.juan.estevez.app.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DOCTOR")
public class Doctor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_DOCTOR")
	private String idDoctor;
	
	@Column(name = "DOSTORS_NAME")
	private String doctorsName;
	
	@Column(name = "ID_TYPE")
	private String idType;
	
	@Column(name = "NUMBER_PROFESSIONAL_CARD")
	private String numberProfessionalCard;
	
	@Column(name = "YEARS_EXPERIENCE")
	private double yearsExperience;
	
	@Column(name = "SPECIALTY")
	private String specialty;
	
	@Column(name = "ATTENTION_START_TIME")
	private Number attentionStartTime;
	
	@Column(name = "ATTENTION_END_TIME")
	private Number attentionEndTime;

}
