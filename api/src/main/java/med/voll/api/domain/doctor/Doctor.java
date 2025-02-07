package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.doctor.dto.DoctorDto;
import med.voll.api.domain.doctor.dto.UpdateDoctor;
import med.voll.api.domain.Enums.Specialty;
import med.voll.api.domain.person.Person;

@Table(name = "doctors")
@Entity(name = "Doctor") //Used to JPQL querys
@Getter
@NoArgsConstructor
public class Doctor extends Person {

  private String crm;

  @Enumerated(EnumType.STRING)
  private Specialty specialty;

  public Doctor(DoctorDto data) {
    super(data.name(), data.email(), data.phone(), data.address());
    this.crm = data.crm();
    this.specialty = data.specialty();
  }

  public void updateData(UpdateDoctor doctorUpdate) {
    super.updateData(doctorUpdate.name(), doctorUpdate.email(), doctorUpdate.address());
  }

}
