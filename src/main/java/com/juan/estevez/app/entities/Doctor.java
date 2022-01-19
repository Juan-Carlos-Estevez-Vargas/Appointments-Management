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

}
