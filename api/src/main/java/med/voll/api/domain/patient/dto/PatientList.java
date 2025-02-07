package med.voll.api.domain.patient.dto;

import med.voll.api.domain.Enums.HealthPlan;
import med.voll.api.domain.Enums.Specialty;
import med.voll.api.domain.patient.Patient;

public record PatientList(long id, String name, String email, HealthPlan healthPlan) {

  public PatientList(Patient patient){
    this(patient.getId(), patient.getName(), patient.getEmail(), patient.getHealthPlan());
  }
}
