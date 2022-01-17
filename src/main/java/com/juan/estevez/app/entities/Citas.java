package com.juan.estevez.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "citas")
public class Citas {
	
	private static final long serialVersionUID = 1L;

	// Mapeo de la tabla citas
	// -----------------------------------------------------------------------
	@Id
	private int idCita;
	@Column
	private String medico;
	@Column
	private String paciente;
	@Column
	private String hora;

	// Constructores de clase
	// ----------------------------------------------------------------------------
	public Citas() {

	}

	public Citas(int idCita, String medico, String paciente, String hora) {
		this.idCita = idCita;
		this.medico = medico;
		this.paciente = paciente;
		this.hora = hora;
	}

	// Getters and Setters
	// ---------------------------------------------------------------------------------
	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
