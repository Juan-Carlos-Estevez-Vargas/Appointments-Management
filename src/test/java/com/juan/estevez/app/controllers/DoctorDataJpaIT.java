package com.juan.estevez.app.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import com.juan.estevez.app.entities.Doctor;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DoctorDataJpaIT {

	private TestRestTemplate testRestTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public DoctorDataJpaIT(TestRestTemplate testRestTemplate, JdbcTemplate jdbcTemplate) {
		this.testRestTemplate = testRestTemplate;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanDoctorToInsert.sql")
	void postDoctor() {
		HttpEntity<Doctor> request = new HttpEntity<>(createDoctor());
		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor", HttpMethod.POST,
				request, Doctor.class);

		List<Map<String, Object>> responseDatabase = jdbcTemplate
				.queryForList("SELECT * FROM DOCTOR WHERE ID_DOCTOR = ?", response.getBody().getIdDoctor());

		assertThat(responseDatabase.size()).isNotNegative().isNotNull();
		assertThat(!responseDatabase.isEmpty());

		/*
		 * Validando que el id del doctor no sea nulo y que la respuesta de la base de
		 * datos coincida con el valor esperado.
		 */
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertThat(responseDatabase.get(0)).isNotEmpty().isNotNull();

		/* Validando el campo ID_DOCTOR */
		assertThat(responseDatabase.get(0).get("ID_DOCTOR")).isNotNull();
		assertEquals(createDoctor().getIdDoctor(), response.getBody().getIdDoctor());
		assertEquals(response.getBody().getIdDoctor(), responseDatabase.get(0).get("ID_DOCTOR"));

		/* Validando el campo DOCTORS_NAME */
		assertThat(responseDatabase.get(0).get("DOCTORS_NAME")).isNotNull();
		assertThat(response.getBody().getDoctorsName()).isNotEmpty();
		assertEquals(createDoctor().getDoctorsName(), response.getBody().getDoctorsName());
		assertEquals(response.getBody().getDoctorsName(), responseDatabase.get(0).get("DOCTORS_NAME"));

		/* Validando el campo ID_TYPE */
		assertThat(responseDatabase.get(0).get("ID_TYPE")).isNotNull();
		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals(createDoctor().getIdType(), response.getBody().getIdType());
		assertEquals(response.getBody().getIdType(), responseDatabase.get(0).get("ID_TYPE"));

		/* Validando el campo NUMBER_PROFESSIONAL_CARD */
		assertThat(responseDatabase.get(0).get("NUMBER_PROFESSIONAL_CARD")).isNotNull();
		assertThat(response.getBody().getNumberProfessionalCard()).isNotEmpty();
		assertEquals(createDoctor().getNumberProfessionalCard(), response.getBody().getNumberProfessionalCard());
		assertEquals(response.getBody().getNumberProfessionalCard(),
				responseDatabase.get(0).get("NUMBER_PROFESSIONAL_CARD"));

		/* Validando el campo YEARS_EXPERIENCE */
		assertThat(responseDatabase.get(0).get("YEARS_EXPERIENCE")).isNotNull();
		assertThat(response.getBody().getYearsExperience()).isNotNull();
		assertThat(response.getBody().getYearsExperience()).isNotNegative();
		assertEquals(createDoctor().getYearsExperience(), response.getBody().getYearsExperience());
		assertEquals(response.getBody().getYearsExperience(), responseDatabase.get(0).get("YEARS_EXPERIENCE"));

		/* Validando el campo SPECIALTY */
		assertThat(responseDatabase.get(0).get("SPECIALTY")).isNotNull();
		assertThat(response.getBody().getSpecialty()).isNotEmpty();
		assertEquals(createDoctor().getSpecialty(), response.getBody().getSpecialty());
		assertEquals(response.getBody().getSpecialty(), responseDatabase.get(0).get("SPECIALTY"));

		/* Validando el campo ATTENTION_START_TIME */
		/*
		 * assertThat(responseDatabase.get(0).get("ATTENTION_START_TIME")).isNotNull();
		 * assertThat(response.getBody().getAttentionStartTime()).isNotNull();
		 * assertThat(response.getBody().getAttentionStartTime()).isNotNegative();
		 * assertEquals(createDoctor().getAttentionStartTime(),
		 * response.getBody().getAttentionStartTime());
		 * assertEquals(response.getBody().getAttentionStartTime(),
		 * responseDatabase.get(0).get("ATTENTION_START_TIME"));
		 */

		/* Validando el campo ATTENTION_END_TIME */
		/*
		 * assertThat(responseDatabase.get(0).get("ATTENTION_END_TIME")).isNotNull();
		 * assertThat(response.getBody().getAttentionEndTime()).isNotNull();
		 * assertThat(response.getBody().getAttentionEndTime()).isNotNegative();
		 * assertEquals(createDoctor().getAttentionEndTime(),
		 * response.getBody().getAttentionEndTime());
		 * assertEquals(response.getBody().getAttentionEndTime(),
		 * responseDatabase.get(0).get("ATTENTION_END_TIME"));
		 */
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorToUpdate.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanDoctorToUpdate.sql")
	void putDoctor() {

		HttpEntity<Doctor> request = new HttpEntity<>(updateDoctor());
		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor", HttpMethod.PUT,
				request, Doctor.class);

		List<Map<String, Object>> responseDatabase = jdbcTemplate
				.queryForList("SELECT * FROM DOCTOR WHERE ID_DOCTOR = ?", response.getBody().getIdDoctor());

		assertThat(responseDatabase.size()).isNotNegative().isNotNull();
		assertThat(!responseDatabase.isEmpty());
		assertThat(responseDatabase.get(0)).isNotEmpty().isNotNull();

		/* Validando el campo ID_DOCTOR */
		assertThat(responseDatabase.get(0).get("ID_DOCTOR")).isNotNull();
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals(updateDoctor().getIdDoctor(), response.getBody().getIdDoctor());
		assertEquals(response.getBody().getIdDoctor(), responseDatabase.get(0).get("ID_DOCTOR"));

		/* Validando el campo DOCTORS_NAME */
		assertThat(responseDatabase.get(0).get("DOCTORS_NAME")).isNotNull();
		assertThat(response.getBody().getDoctorsName()).isNotEmpty();
		assertEquals(updateDoctor().getDoctorsName(), response.getBody().getDoctorsName());
		assertEquals(response.getBody().getDoctorsName(), responseDatabase.get(0).get("DOCTORS_NAME"));

		/* Validando el campo ID_TYPE */
		assertThat(responseDatabase.get(0).get("ID_TYPE")).isNotNull();
		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals(updateDoctor().getIdType(), response.getBody().getIdType());
		assertEquals(response.getBody().getIdType(), responseDatabase.get(0).get("ID_TYPE"));

		/* Validando el campo NUMBER_PROFESSIONAL_CARD */
		assertThat(responseDatabase.get(0).get("NUMBER_PROFESSIONAL_CARD")).isNotNull();
		assertThat(response.getBody().getNumberProfessionalCard()).isNotEmpty();
		assertEquals(updateDoctor().getNumberProfessionalCard(), response.getBody().getNumberProfessionalCard());
		assertEquals(response.getBody().getNumberProfessionalCard(),
				responseDatabase.get(0).get("NUMBER_PROFESSIONAL_CARD"));

		/* Validando el campo YEARS_EXPERIENCE */
		assertThat(responseDatabase.get(0).get("YEARS_EXPERIENCE")).isNotNull();
		assertThat(response.getBody().getYearsExperience()).isNotNull();
		assertThat(response.getBody().getYearsExperience()).isNotNegative();
		assertEquals(updateDoctor().getYearsExperience(), response.getBody().getYearsExperience());
		assertEquals(response.getBody().getYearsExperience(), responseDatabase.get(0).get("YEARS_EXPERIENCE"));

		/* Validando el campo SPECIALTY */
		assertThat(responseDatabase.get(0).get("SPECIALTY")).isNotNull();
		assertThat(response.getBody().getSpecialty()).isNotEmpty();
		assertEquals(updateDoctor().getSpecialty(), response.getBody().getSpecialty());
		assertEquals(response.getBody().getSpecialty(), responseDatabase.get(0).get("SPECIALTY"));

		/* Validando el campo ATTENTION_START_TIME */
		/*
		 * assertThat(responseDatabase.get(0).get("ATTENTION_START_TIME")).isNotNull();
		 * assertThat(response.getBody().getAttentionStartTime()).isNotNull();
		 * assertThat(response.getBody().getAttentionStartTime()).isNotNegative();
		 * assertEquals(updateDoctor().getAttentionStartTime(),
		 * response.getBody().getAttentionStartTime());
		 * assertEquals(response.getBody().getAttentionStartTime(),
		 * responseDatabase.get(0).get("ATTENTION_START_TIME"));
		 * 
		 */
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorsToGet.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanDoctorsToGet.sql")
	void getDoctor() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Doctor> request = new HttpEntity<>(headers);
		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor/findById/101011",
				HttpMethod.GET, request, Doctor.class);

		List<Map<String, Object>> responseDatabase = jdbcTemplate
				.queryForList("SELECT * FROM DOCTOR WHERE ID_DOCTOR = ?", response.getBody().getIdDoctor());

		assertThat(responseDatabase.size()).isNotNegative().isNotNull();
		assertThat(!responseDatabase.isEmpty());
		assertThat(responseDatabase.get(0)).isNotEmpty().isNotNull();

		/* Validando el campo ID_DOCTOR */
		assertThat(responseDatabase.get(0).get("ID_DOCTOR")).isNotNull();
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals("101011", response.getBody().getIdDoctor());
		assertEquals(response.getBody().getIdDoctor(), responseDatabase.get(0).get("ID_DOCTOR"));

		/* Validando el campo DOCTORS_NAME */
		assertThat(responseDatabase.get(0).get("DOCTORS_NAME")).isNotNull();
		assertThat(response.getBody().getDoctorsName()).isNotEmpty();
		// assertEquals(updateDoctor().getDoctorsName(),
		// response.getBody().getDoctorsName());
		assertEquals(response.getBody().getDoctorsName(), responseDatabase.get(0).get("DOCTORS_NAME"));

		/* Validando el campo ID_TYPE */
		assertThat(responseDatabase.get(0).get("ID_TYPE")).isNotNull();
		assertThat(response.getBody().getIdType()).isNotEmpty();
		// assertEquals(updateDoctor().getIdType(), response.getBody().getIdType());
		assertEquals(response.getBody().getIdType(), responseDatabase.get(0).get("ID_TYPE"));

		/* Validando el campo NUMBER_PROFESSIONAL_CARD */
		assertThat(responseDatabase.get(0).get("NUMBER_PROFESSIONAL_CARD")).isNotNull();
		assertThat(response.getBody().getNumberProfessionalCard()).isNotEmpty();
		// assertEquals(updateDoctor().getNumberProfessionalCard(),
		// response.getBody().getNumberProfessionalCard());
		assertEquals(response.getBody().getNumberProfessionalCard(),
				responseDatabase.get(0).get("NUMBER_PROFESSIONAL_CARD"));

		/* Validando el campo YEARS_EXPERIENCE */
		assertThat(responseDatabase.get(0).get("YEARS_EXPERIENCE")).isNotNull();
		assertThat(response.getBody().getYearsExperience()).isNotNull();
		assertThat(response.getBody().getYearsExperience()).isNotNegative();
		// assertEquals(updateDoctor().getYearsExperience(),
		// response.getBody().getYearsExperience());
		assertEquals(response.getBody().getYearsExperience(), responseDatabase.get(0).get("YEARS_EXPERIENCE"));

		/* Validando el campo SPECIALTY */
		assertThat(responseDatabase.get(0).get("SPECIALTY")).isNotNull();
		assertThat(response.getBody().getSpecialty()).isNotEmpty();
		// assertEquals(updateDoctor().getSpecialty(),
		// response.getBody().getSpecialty());
		assertEquals(response.getBody().getSpecialty(), responseDatabase.get(0).get("SPECIALTY"));
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorToDelete.sql")
	void deleteDoctor() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Doctor> request = new HttpEntity<>(headers);
		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor/11110",
				HttpMethod.DELETE, request, Doctor.class);

		List<Map<String, Object>> responseDatabase = jdbcTemplate
				.queryForList("SELECT * FROM DOCTOR WHERE ID_DOCTOR = ?", response.getBody().getIdDoctor());

		assertThat(responseDatabase.size()).isNotNegative();
		assertThat(responseDatabase.isEmpty());

	}

	private Doctor createDoctor() {
		Doctor doctor = new Doctor();
		doctor.setIdDoctor("1010");
		doctor.setDoctorsName("Doctor Test");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("1111");
		doctor.setYearsExperience(10);
		doctor.setSpecialty("Cardiología");
		doctor.setAttentionStartTime(9.9);
		doctor.setAttentionEndTime(16.0);
		return doctor;
	}

	private Doctor updateDoctor() {
		Doctor doctor = new Doctor();
		doctor.setIdDoctor("101010");
		doctor.setDoctorsName("Doctor Update Test");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("1111");
		doctor.setYearsExperience(4);
		doctor.setSpecialty("Cardiología");
		doctor.setAttentionStartTime(8.0);
		doctor.setAttentionEndTime(16.0);
		return doctor;
	}
}
