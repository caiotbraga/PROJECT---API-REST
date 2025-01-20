package med.voll.api.DTO.Doctor;

import med.voll.api.Enums.Specialty;
import med.voll.api.Models.Address;
import med.voll.api.Models.Doctor;

public record DoctorDetail(String name, String email, String crm, Specialty specialty, Address address) {

  public DoctorDetail(Doctor doctor){
    this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress());
  }
}
