package med.voll.api.Models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.DTO.AddressDto;

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

  public void updateData(AddressDto address) {
    if(address.publicPlace() != null){
      this.publicPlace = address.publicPlace();
    }
    if(address.complement() != null){
      this.complement = address.complement();
    }
    if(address.neighborhood() != null){
      this.neighborhood = address.neighborhood();
    }
    if(address.city() != null){
      this.city = address.city();
    }
    if(address.state() != null){
      this.state = address.state();
    }
    if(address.postalCode() != null){
      this.postalCode = address.postalCode();
    }

  }
}
