package med.voll.api.domain.consultation.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Enums.Specialty;
import med.voll.api.domain.consultation.ScheduleConsultation;

import java.time.LocalDateTime;

public record ScheduleConsultationInfo(

    @NotNull
    Long patientId,

    @NotNull
    Long doctorId,

    @NotNull
    Specialty specialty,

    @NotNull
    @Future LocalDateTime date
) {

  public ScheduleConsultationInfo(ScheduleConsultation consultation) {
    this(consultation.getPatient().getId(), consultation.getDoctor().getId(), consultation.getDoctor()
        .getSpecialty(), consultation.getConsultation_date());
  }
}
