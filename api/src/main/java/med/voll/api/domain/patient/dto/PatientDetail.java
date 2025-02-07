package med.voll.api.domain.patient.dto;

import med.voll.api.domain.Enums.HealthPlan;
import med.voll.api.domain.Enums.Specialty;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.patient.Patient;

public record PatientDetail(String name, String email, HealthPlan healthPlan, Address address) {

  public PatientDetail(Patient patient){
    this(patient.getName(), patient.getEmail(), patient.getHealthPlan(), patient.getAddress());
  }
}
