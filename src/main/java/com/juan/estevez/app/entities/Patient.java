package com.juan.estevez.app.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PATIENT")
public class Patient implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_PATIENT")
	private String idPatient;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DATE_OF_BIRTH")
	private String dateOfBirth;
	
	@Column(name = "ID_TYPE")
	private String idType;
	
	@Column(name = "EPS")
	private String eps;
	
	@Column(name = "CLINIC_HISTORY")
	private String clinicHistory;

}
