package com.juan.estevez.app.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PATIENT")
public class Patient implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String idPatient;
	@Column
	private String name;
	@Column
	private String dateOfBirth;
	@Column
	private String idType;
	@Column
	private String eps;
	@Column
	private String clinicHistory;

}
