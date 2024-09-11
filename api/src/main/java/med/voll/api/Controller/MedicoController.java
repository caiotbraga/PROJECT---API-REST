package med.voll.api.Controller;

import med.voll.api.DTO.DoctorDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctor")
public class MedicoController {

  @PostMapping("/register")
  public void doctorRegister(@RequestBody DoctorDto doctorData){
    System.out.println(doctorData);
  }
}
