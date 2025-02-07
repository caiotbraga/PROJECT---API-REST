package med.voll.api.domain.person;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressDto;
import med.voll.api.domain.doctor.dto.UpdateDoctor;

@MappedSuperclass
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class Person {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String phone;
  private Boolean active;
  @Embedded
  private Address address;

  public Person(String name, String email,String phone, AddressDto address){
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = new Address(address);
    this.active = true;
  }

  public void updateData(String name, String email, AddressDto address) {
    if(name != null){
      this.name = name;
    }
    if(email != null){
      this.email = email;
    }
    if(address != null){
      this.address.updateData(address);
    }
  }

  public void delete() {
    this.active  = false;
  }

}
