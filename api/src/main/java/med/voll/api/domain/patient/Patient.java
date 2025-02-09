package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.Enums.HealthPlan;
import med.voll.api.domain.address.AddressDto;
import med.voll.api.domain.patient.dto.PatientDto;
import med.voll.api.domain.patient.dto.UpdatePatient;
import med.voll.api.domain.person.Person;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
public class Patient extends Person {

  @Enumerated(EnumType.STRING)
  @Column(name = "health_plan")
  private HealthPlan healthPlan;

  public Patient(PatientDto data){
    super(data.name(), data.email(), data.phone(), data.address());
    this.healthPlan = data.healthPlan();
  }

  public void updateData(UpdatePatient patient) {
    super.updateData(patient.name(), patient.email(), patient.address());
    if(patient.healthPlan() != null){
      this.healthPlan = patient.healthPlan();
    }
  }
}
