package med.voll.api.domain.consultation.service;

import med.voll.api.domain.consultation.ScheduleConsultation;
import med.voll.api.domain.consultation.ScheduleConsultationRepository;
import med.voll.api.domain.consultation.dto.ScheduleConsultationData;
import med.voll.api.domain.consultation.dto.ScheduleConsultationInfo;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.exception.ValidateDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleConsultationService {

  @Autowired
  DoctorRepository doctorRepository;

  @Autowired
  PatientRepository patientRepository;

  @Autowired
  ScheduleConsultationRepository scheduleConsultationRepository;

  public ScheduleConsultationInfo  toSchedule(ScheduleConsultationData data) {
    if(!patientRepository.existsById(data.patientId())){
      throw new ValidateDataException("Patient id do not exists!");
    }

    if(data.doctorId() != null && !doctorRepository.existsById(data.doctorId())){
      throw new ValidateDataException("Doctor id do not exists!");
    }

    var patient = patientRepository.getReferenceById(data.patientId());
    var doctor = chooseDoctor(data);
    var consultation = new ScheduleConsultation(null, patient, doctor, data.date());
    scheduleConsultationRepository.save(consultation);
    return new ScheduleConsultationInfo(consultation);
  }

  private Doctor chooseDoctor(ScheduleConsultationData data) {
    if(data.doctorId() != null){
      return doctorRepository.getReferenceById(data.doctorId());
    }

    if(data.specialty() == null){
      throw new ValidateDataException("If any doctor was selected, a specialty must be provided.");
    }

    return doctorRepository.getRandomDoctorBySpecialtyOnFreeDate(data.specialty(), data.date());
  }

}
