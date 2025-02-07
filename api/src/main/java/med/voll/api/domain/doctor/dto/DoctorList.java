package med.voll.api.domain.doctor.dto;

import med.voll.api.domain.Enums.Specialty;
import med.voll.api.domain.doctor.Doctor;

public record DoctorList(long id, String name, String email, String crm, Specialty specialty) {

  public DoctorList(Doctor doctor){
    this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
  }
}
