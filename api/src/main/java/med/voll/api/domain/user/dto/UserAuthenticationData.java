package med.voll.api.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserAuthenticationData(@NotBlank String login, @NotBlank String password) {
}
