package med.voll.api.domain.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.Enums.HealthPlan;
import med.voll.api.domain.address.AddressDto;

public record PatientDto(

    @NotBlank
    String name,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Pattern(regexp = "\\d{11,13}")
    String phone,

    @NotNull HealthPlan healthPlan,

    @NotNull
    @Valid AddressDto address
) {
}
