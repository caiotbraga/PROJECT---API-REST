package med.voll.api.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.DTO.Doctor.DoctorDto;
import med.voll.api.DTO.Doctor.DoctorList;
import med.voll.api.DTO.Doctor.UpdateDoctor;
import med.voll.api.Models.Doctor;
import med.voll.api.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class MedicoController {

  @Autowired
  DoctorRepository repository;

  @PostMapping("/register")
  @Transactional
  public void doctorRegister(@RequestBody @Valid DoctorDto doctorData){
    repository.save(new Doctor(doctorData));
  }

  @GetMapping("/list")
  public Page<DoctorList> doctorList(Pageable pagination){
    return repository.findAllByActiveTrue(pagination).map(DoctorList::new);
  }

  @PutMapping("/edit")
  @Transactional
  public void  doctorUpdate(@RequestBody @Valid UpdateDoctor doctorUpdate){
    var doctor = repository.getReferenceById(doctorUpdate.id());
    doctor.updateData(doctorUpdate);
  }

  @DeleteMapping("/delete/{id}")
  public void doctorDelete(@PathVariable Long id){
    var doctor = repository.getReferenceById(id);
    doctor.delete();
  }
}
