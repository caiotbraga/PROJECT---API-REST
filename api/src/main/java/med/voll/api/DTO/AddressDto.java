package med.voll.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddressDto(

    @NotBlank
    String publicPlace,

    int number,

    String complement,

    @NotBlank
    String neighborhood,

    @NotBlank
    String city,

    @NotBlank
    String state,

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    String postalCode
) {
}
