package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.dto.DoctorDetail;
import med.voll.api.domain.doctor.dto.DoctorDto;
import med.voll.api.domain.doctor.dto.DoctorList;
import med.voll.api.domain.doctor.dto.UpdateDoctor;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.dto.PatientDetail;
import med.voll.api.domain.patient.dto.PatientDto;
import med.voll.api.domain.patient.dto.PatientList;
import med.voll.api.domain.patient.dto.UpdatePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
@SecurityRequirement(name = "bearer-key")
public class    PatientController {

  @Autowired
  PatientRepository repository;

  @PostMapping("/register")
  @Transactional
  public ResponseEntity patientRegister(@RequestBody @Valid PatientDto patientData, UriComponentsBuilder uriComponentsBuilder){
    Patient patient = new Patient(patientData);
    repository.save(patient);
    var uri = uriComponentsBuilder.path("/patient/register/{id}").buildAndExpand(patient.getId()).toUri();
    return ResponseEntity.created(uri).body(patientData);
  }

  @GetMapping("/list")
  public ResponseEntity<Page<PatientList>> patientList(Pageable pagination){
    Page<PatientList> patientList = repository.findAllByActiveTrue(pagination).map(PatientList::new);
    return ResponseEntity.ok(patientList);
  }

  @PutMapping("/edit")
  @Transactional
  public ResponseEntity  patientUpdate(@RequestBody @Valid UpdatePatient patientUpdate){
    var patient = repository.getReferenceById(patientUpdate.id());
    patient.updateData(patientUpdate);
    return ResponseEntity.ok(new PatientDetail(patient));
  }

  @DeleteMapping("/delete/{id}")
  @Transactional
  public ResponseEntity patientDelete(@PathVariable Long id){
    var patient = repository.getReferenceById(id);
    patient.delete();
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/detail/{id}")
  @Transactional
  public ResponseEntity patientDetail(@PathVariable Long  id){
    var patient = repository.getReferenceById(id);
    return ResponseEntity.ok(new PatientDetail(patient));
  }
}
