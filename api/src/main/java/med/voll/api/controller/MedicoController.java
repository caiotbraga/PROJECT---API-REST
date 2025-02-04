package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.dto.DoctorDetail;
import med.voll.api.domain.doctor.dto.DoctorDto;
import med.voll.api.domain.doctor.dto.DoctorList;
import med.voll.api.domain.doctor.dto.UpdateDoctor;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctor")
public class MedicoController {

  @Autowired
  DoctorRepository repository;

  @PostMapping("/register")
  @Transactional
  public ResponseEntity doctorRegister(@RequestBody @Valid DoctorDto doctorData, UriComponentsBuilder uriComponentsBuilder){
    Doctor doctor = new Doctor(doctorData);
    repository.save(doctor);
    var uri = uriComponentsBuilder.path("/doctor/register/{id}").buildAndExpand(doctor.getId()).toUri();
    return ResponseEntity.created(uri).body(doctorData);
  }

  @GetMapping("/list")
  public ResponseEntity<Page<DoctorList>> doctorList(Pageable pagination){
    Page<DoctorList> doctorList = repository.findAllByActiveTrue(pagination).map(DoctorList::new);
    return ResponseEntity.ok(doctorList);
  }

  @PutMapping("/edit")
  @Transactional
  public ResponseEntity  doctorUpdate(@RequestBody @Valid UpdateDoctor doctorUpdate){
    var doctor = repository.getReferenceById(doctorUpdate.id());
    doctor.updateData(doctorUpdate);
    return ResponseEntity.ok(new DoctorDetail(doctor));
  }

  @DeleteMapping("/delete/{id}")
  @Transactional
  public ResponseEntity doctorDelete(@PathVariable Long id){
    var doctor = repository.getReferenceById(id);
    doctor.delete();
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/detail/{id}")
  @Transactional
  public ResponseEntity doctorDetail(@PathVariable Long  id){
    var doctor = repository.getReferenceById(id);
    return ResponseEntity.ok(new DoctorDetail(doctor));
  }
}
