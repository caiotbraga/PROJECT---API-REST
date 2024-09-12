package med.voll.api.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.Enums.Specialty;

public record DoctorDto(

    @NotBlank
    String name,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Pattern(regexp = "\\d{11,13}")
    String phone,

    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String crm,

    @NotNull
    Specialty specialty,

    @NotNull
    @Valid
    AddressDto address
) {
}
