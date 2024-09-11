package med.voll.api.Models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.DTO.AddressDto;
import med.voll.api.DTO.DoctorDto;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  private String publicPlace;
  private int number;
  private String complement;
  private String neighborhood;
  private String city;
  private String state;
  private String postalCode;

  public Address(AddressDto data) {
    this.city = data.city();
    this.complement = data.complement();
    this.neighborhood = data.state();
    this.number = data.number();
    this.postalCode = data.postalCode();
    this.publicPlace = data.publicPlace();
    this.state = data.state();
  }
}
