package med.voll.api.domain.doctor.dto;

import med.voll.api.Enums.Specialty;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.doctor.Doctor;

public record DoctorDetail(String name, String email, String crm, Specialty specialty, Address address) {

  public DoctorDetail(Doctor doctor){
    this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress());
  }
}
