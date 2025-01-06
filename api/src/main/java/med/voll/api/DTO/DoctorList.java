package med.voll.api.DTO;

import med.voll.api.Enums.Specialty;
import med.voll.api.Models.Doctor;

public record DoctorList(String name, String email, String crm, Specialty specialty) {

  public DoctorList(Doctor doctor){
    this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
  }
}
