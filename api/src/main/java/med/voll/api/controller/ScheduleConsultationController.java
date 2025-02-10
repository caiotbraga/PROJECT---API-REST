package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultation.service.ScheduleCancellationService;
import med.voll.api.domain.consultation.service.ScheduleConsultationService;
import med.voll.api.domain.consultation.dto.CancellationAppointmentData;
import med.voll.api.domain.consultation.dto.ScheduleConsultationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultation")
@SecurityRequirement(name = "bearer-key")
public class ScheduleConsultationController {

  @Autowired
  ScheduleConsultationService scheduleConsultation;

  @Autowired
  ScheduleCancellationService scheduleCancellation;

  @PostMapping("/toSchedule")
  @Transactional
  public ResponseEntity scheduleConsultation(@RequestBody @Valid ScheduleConsultationData data){
    scheduleConsultation.toSchedule(data);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/cancel")
  @Transactional
  public ResponseEntity cancelattionAppointment(@RequestBody @Valid CancellationAppointmentData data){
    scheduleCancellation.cancel(data);
    return ResponseEntity.noContent().build();
  }

}
