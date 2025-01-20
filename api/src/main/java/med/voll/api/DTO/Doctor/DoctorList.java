package med.voll.api.DTO.Doctor;

import med.voll.api.Enums.Specialty;
import med.voll.api.Models.Doctor;

public record DoctorList(long id, String name, String email, String crm, Specialty specialty) {

  public DoctorList(Doctor doctor){
    this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
  }
}
