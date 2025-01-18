package med.voll.api.DTO.Doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.DTO.AddressDto;

public record UpdateDoctor(@NotNull Long id, String name, String email, AddressDto address) {
}
