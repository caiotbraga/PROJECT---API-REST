package med.voll.api.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.DTO.DoctorDto;
import med.voll.api.DTO.DoctorList;
import med.voll.api.Models.Doctor;
import med.voll.api.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    return repository.findAll(pagination).map(DoctorList::new);
  }
}
