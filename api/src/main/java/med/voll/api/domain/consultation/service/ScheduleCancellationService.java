package med.voll.api.domain.consultation.service;

import med.voll.api.domain.consultation.ScheduleConsultationRepository;
import med.voll.api.domain.consultation.dto.CancellationAppointmentData;
import med.voll.api.infra.exception.CancellationNotAllowedException;
import med.voll.api.infra.exception.ValidateDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ScheduleCancellationService {

  @Autowired
  ScheduleConsultationRepository scheduleConsultationRepository;

  public void cancel(CancellationAppointmentData data) {
    if(!scheduleConsultationRepository.existsById(data.scheduleConsultationId())){
      throw new ValidateDataException("Appointment do not exists");
    }
    if(data.reasonForCancellation() == null){
      throw new ValidateDataException("Reason for cancellation must be provided.");
    }
    LocalDateTime cancellationDate = LocalDateTime.now();
    checkDateCancellation(data, cancellationDate);
    scheduleConsultationRepository.deleteById(data.scheduleConsultationId());
  }

  private void checkDateCancellation(CancellationAppointmentData data, LocalDateTime time) {
    var scheduleConsultation = scheduleConsultationRepository.getReferenceById(data.scheduleConsultationId());
    long diferencaHoras = Duration.between(time, scheduleConsultation.getConsultation_date()).toHours();
    if(diferencaHoras < 24){
      throw new CancellationNotAllowedException();
    }
  }
}


