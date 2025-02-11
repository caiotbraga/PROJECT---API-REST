package med.voll.api.domain.consultation.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Enums.Specialty;

import java.time.LocalDateTime;

public record ScheduleConsultationData(
    @NotNull
    Long patientId,

    Long doctorId,

    Specialty specialty,

    @NotNull
    @Future
    LocalDateTime date) {
}

