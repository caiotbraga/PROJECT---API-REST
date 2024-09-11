package med.voll.api.Controller;

import med.voll.api.DTO.DoctorDto;
import med.voll.api.Models.Doctor;
import med.voll.api.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctor")
public class MedicoController {

  @Autowired
  DoctorRepository repository;

  @PostMapping("/register")
  public void doctorRegister(@RequestBody DoctorDto doctorData){
    repository.save(new Doctor(doctorData));
  }
}
