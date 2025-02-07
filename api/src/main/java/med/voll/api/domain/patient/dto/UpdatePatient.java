package med.voll.api.domain.patient.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Enums.HealthPlan;
import med.voll.api.domain.address.AddressDto;

public record UpdatePatient(@NotNull Long id, String name, String email, HealthPlan healthPlan, AddressDto address) {
}
