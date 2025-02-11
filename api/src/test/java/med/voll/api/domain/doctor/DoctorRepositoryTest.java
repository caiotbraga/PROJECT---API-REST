package med.voll.api.domain.doctor;

import med.voll.api.domain.Enums.HealthPlan;
import med.voll.api.domain.Enums.Specialty;
import med.voll.api.domain.address.AddressDto;
import med.voll.api.domain.consultation.ScheduleConsultation;
import med.voll.api.domain.doctor.dto.DoctorDto;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.dto.PatientDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private TestEntityManager em;

  @Test
  @DisplayName("Must return null when the only doctor registred isn't available on date.")
  void getRandomDoctorBySpecialtyOnFreeDateScenario01() {
    //Given or arrange
    var nextMonday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(10).withMinute(0).withSecond(0).withNano(0);;
    var doctor = registerDoctor("Dr. Paulo", "paulo@doctor.com", "12345", Specialty.CARDIOLOGY);
    var patient = registerPatient("Caio", HealthPlan.SULAMERICA, "caio@patient.com");
    registerConsultation(doctor, patient, nextMonday);

    //when or act
    var freeDoctor = doctorRepository.getRandomDoctorBySpecialtyOnFreeDate(Specialty.CARDIOLOGY, nextMonday);

    //then or assert
    assertThat(freeDoctor).isNull();
  }

  @Test
  @DisplayName("Must return doctor when is available on date.")
  void getRandomDoctorBySpecialtyOnFreeDateScenario02() {
    //Given or arrange
    var nextMonday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(10).withMinute(0).withSecond(0).withNano(0);;
    var doctor = registerDoctor("Dr. Paulo", "paulo@doctor.com", "12345", Specialty.CARDIOLOGY);

    //when or act
    var freeDoctor = doctorRepository.getRandomDoctorBySpecialtyOnFreeDate(Specialty.CARDIOLOGY, nextMonday);

    //then or assert
    assertThat(freeDoctor).isEqualTo(doctor);
  }

  @Test
  @DisplayName("Must return null when there are doctors registered, but none with the requested specialty.")
  void getRandomDoctorBySpecialtyOnFreeDateScenario03() {
    // Given or arrange
    var nextMonday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(10).withMinute(0).withSecond(0).withNano(0);

    // Registrando médicos, mas com especialidades diferentes
    var doctor1 = registerDoctor("Dr. Lucas", "lucas@doctor.com", "11111", Specialty.DERMATOLOGY);
    var doctor2 = registerDoctor("Dr. Fernanda", "fernanda@doctor.com", "22222", Specialty.GYNECOLOGY);

    // When or act
    var freeDoctor = doctorRepository.getRandomDoctorBySpecialtyOnFreeDate(Specialty.CARDIOLOGY, nextMonday);

    // Then or assert
    assertThat(freeDoctor).isNull();
  }

  @Test
  @DisplayName("Must return one doctor when multiple doctors are available on the given date.")
  void getRandomDoctorBySpecialtyOnFreeDateScenario04() {
    // Given or arrange
    var nextMonday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(10).withMinute(0).withSecond(0).withNano(0);
    var doctor1 = registerDoctor("Dr. Paulo", "paulo@doctor.com", "12345", Specialty.CARDIOLOGY);
    var doctor2 = registerDoctor("Dr. Ana", "ana@doctor.com", "67890", Specialty.CARDIOLOGY);

    // When or act
    var freeDoctor = doctorRepository.getRandomDoctorBySpecialtyOnFreeDate(Specialty.CARDIOLOGY, nextMonday);

    // Then or assert
    assertThat(freeDoctor).isIn(doctor1, doctor2);
  }

  @Test
  @DisplayName("Must return null when all doctors of a specialty are unavailable on the given date.")
  void getRandomDoctorBySpecialtyOnFreeDateScenario05() {
    // Given or arrange
    var nextMonday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(10).withMinute(0).withSecond(0).withNano(0);
    var doctor1 = registerDoctor("Dr. Paulo", "paulo@doctor.com", "12345", Specialty.CARDIOLOGY);
    var doctor2 = registerDoctor("Dr. Ana", "ana@doctor.com", "67890", Specialty.CARDIOLOGY);
    var patient1 = registerPatient("Caio", HealthPlan.SULAMERICA, "caio@patient.com");
    var patient2 = registerPatient("Maria", HealthPlan.CASSI, "maria@patient.com");

    registerConsultation(doctor1, patient1, nextMonday);
    registerConsultation(doctor2, patient2, nextMonday);

    // When or act
    var freeDoctor = doctorRepository.getRandomDoctorBySpecialtyOnFreeDate(Specialty.CARDIOLOGY, nextMonday);

    // Then or assert
    assertThat(freeDoctor).isNull();
  }

  @Test
  @DisplayName("Must return an available doctor when not all doctors of the specialty are busy.")
  void getRandomDoctorBySpecialtyOnFreeDateScenario06() {
    // Given or arrange
    var nextMonday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(10).withMinute(0).withSecond(0).withNano(0);
    var doctor1 = registerDoctor("Dr. Paulo", "paulo@doctor.com", "12345", Specialty.CARDIOLOGY);
    var doctor2 = registerDoctor("Dr. Ana", "ana@doctor.com", "67890", Specialty.CARDIOLOGY);
    var patient = registerPatient("Caio", HealthPlan.SULAMERICA, "caio@patient.com");

    // Apenas um médico está ocupado
    registerConsultation(doctor1, patient, nextMonday);

    // When or act
    var freeDoctor = doctorRepository.getRandomDoctorBySpecialtyOnFreeDate(Specialty.CARDIOLOGY, nextMonday);

    // Then or assert
    assertThat(freeDoctor).isEqualTo(doctor2);
  }

  private void registerConsultation(Doctor doctor, Patient patient, LocalDateTime date) {
    em.persist(new ScheduleConsultation(null, patient, doctor, date));
  }

  private Doctor registerDoctor(String name, String email, String crm, Specialty specialty) {
    var doctor = new Doctor(doctorData(name, email, crm, specialty));
    em.persist(doctor);
    return doctor;
  }

  private Patient registerPatient(String name, HealthPlan healthPlan, String email) {
    var patient = new Patient(patientData(name, healthPlan, email));
    em.persist(patient);
    return patient;
  }

  private DoctorDto doctorData(String nome, String email, String crm, Specialty specialty) {
    return new DoctorDto(
        nome,
        email,
        "00000000000",
        crm,
        specialty,
        addressData()
    );
  }

  private PatientDto patientData(String nome, HealthPlan healthPlan, String email) {
    return new PatientDto(
        nome,
        email,
        "00000000000",
        healthPlan,
        addressData()
    );
  }

  private AddressDto addressData() {
    return new AddressDto(
        "rua xpto",
        405,
        "00000000",
        "Brasilia",
        "Recife",
        "PE",
        "52040150"
    );
  }
}


