package com.juan.estevez.app.entities;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medico")
public class Medico {
	
	private static final long serialVersionUID = 1L;

	// Mapeo de la tabla medico
	// -----------------------------------------------------------------------
	@Id
	private String idMedico;
	@Column
	private String nombreMedico;
	@Column
	private String tipoIdentificacion;
	@Column
	private String NTarjetaProfesional;
	@Column
	private double aniosExperiencia;
	@Column
	private String especialidad;
	@Column
	private String horaInicioAtencion;
	@Column
	private String horaFinAtencion;

	// Constructores de clase
	// ----------------------------------------------------------------------------
	public Medico() {
	}

	public Medico(String nombreMedico, String idMedico, String tipoIdentificacion, String nTarjetaProfesional,
			double aniosExperiencia, String especialidad, String horaInicioAtencion, String horaFinAtencion) {
		this.nombreMedico = nombreMedico;
		this.idMedico = idMedico;
		this.tipoIdentificacion = tipoIdentificacion;
		NTarjetaProfesional = nTarjetaProfesional;
		this.aniosExperiencia = aniosExperiencia;
		this.especialidad = especialidad;
		this.horaInicioAtencion = horaInicioAtencion;
		this.horaFinAtencion = horaFinAtencion;
	}

	// Getters and Setters
	// ---------------------------------------------------------------------------------
	public String getNombreMedico() {
		return nombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

	public String getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(String idMedico) {
		this.idMedico = idMedico;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNTarjetaProfesional() {
		return NTarjetaProfesional;
	}

	public void setNTarjetaProfesional(String nTarjetaProfesional) {
		NTarjetaProfesional = nTarjetaProfesional;
	}

	public double getAniosExperiencia() {
		return aniosExperiencia;
	}

	public void setAniosExperiencia(double aniosExperiencia) {
		this.aniosExperiencia = aniosExperiencia;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getHoraInicioAtencion() {
		return horaInicioAtencion;
	}

	public void setHoraInicioAtencion(String horaInicioAtencion) {
		this.horaInicioAtencion = horaInicioAtencion;
	}

	public String getHoraFinAtencion() {
		return horaFinAtencion;
	}

	public void setHoraFinAtencion(String horaFinAtencion) {
		this.horaFinAtencion = horaFinAtencion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
