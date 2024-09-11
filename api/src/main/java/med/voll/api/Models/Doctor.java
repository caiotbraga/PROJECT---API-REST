package med.voll.api.Models;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.DTO.DoctorDto;
import med.voll.api.Enums.Specialty;

@Table(name = "Doctors")
@Entity(name = "Doctor") //Used to JPQL querys
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String crm;
  private String phone;

  @Enumerated(EnumType.STRING)
  private Specialty specialty;

  @Embedded
  private Address address;

  public Doctor(DoctorDto data) {
    this.name = data.name();
    this.address = new Address(data.address());
    this.crm = data.crm();
    this.email = data.email();
    this.specialty = data.specialty();
    this.phone = data.phone();
  }
}
