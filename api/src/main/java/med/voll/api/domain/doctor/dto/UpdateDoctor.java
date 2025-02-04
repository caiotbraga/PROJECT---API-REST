package med.voll.api.domain.doctor.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDto;

public record UpdateDoctor(@NotNull Long id, String name, String email, AddressDto address) {
}
