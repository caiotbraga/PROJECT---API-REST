package med.voll.api.Models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
