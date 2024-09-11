package med.voll.api.Models;

import jakarta.persistence.*;
import lombok.*;
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

  @Enumerated(EnumType.STRING)
  private Specialty specialty;

  @Embedded
  private Address address;


}
