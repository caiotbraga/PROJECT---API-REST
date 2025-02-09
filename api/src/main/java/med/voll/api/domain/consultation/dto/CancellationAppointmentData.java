package med.voll.api.domain.consultation.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Enums.ReasonForCancellation;

public record CancellationAppointmentData(

    @NotNull
    Long scheduleConsultationId,

    @NotNull ReasonForCancellation reasonForCancellation

) {
}
