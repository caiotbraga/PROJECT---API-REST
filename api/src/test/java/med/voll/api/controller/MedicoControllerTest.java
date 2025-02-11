package med.voll.api.controller;

import med.voll.api.domain.Enums.Specialty;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressDto;
import med.voll.api.domain.consultation.dto.ScheduleConsultationData;
import med.voll.api.domain.consultation.dto.ScheduleConsultationInfo;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.doctor.dto.DoctorDetail;
import med.voll.api.domain.doctor.dto.DoctorDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WithMockUser
class MedicoControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private JacksonTester<DoctorDto> requestJson;

  @Autowired
  private JacksonTester<DoctorDetail> responseJson;

  @MockBean
  private DoctorRepository repository;

  @Test
  @DisplayName("Must return status 400 when data is invalid")
  void doctorRegisterScenario01() throws Exception {
    var response = mvc.perform(post("/doctor/register")).andReturn().getResponse();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  @DisplayName("Must return status 200 when all data is valid")
  void doctorRegisterScenario02() throws Exception {
    var registerData = new DoctorDto(
        "Medico",
        "medico@voll.med",
        "61999999999",
        "123456",
        Specialty.CARDIOLOGY,
        addressData()
    );

    when(repository.save(any())).thenReturn(new Doctor(registerData));

    var response = mvc.perform(post("/doctor/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestJson.write(registerData)
            .getJson())).andReturn().getResponse();

    var doctorDetail = new DoctorDetail(
        null,
        registerData.name(),
        registerData.email(),
        registerData.crm(),
        registerData.specialty(),
        new Address(registerData.address())
    );


    var jsonExpected = responseJson.write(doctorDetail).getJson();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
  }


  @Test
  void doctorList() {
  }

  @Test
  void doctorUpdate() {
  }

  @Test
  void doctorDelete() {
  }

  @Test
  void doctorDetail() {
  }

  private AddressDto addressData() {
    return new AddressDto(
        "rua xpto",
        405,
        "00000000",
        "Brasilia",
        "Recife",
        "PE",
        "52040150"
    );
  }
}
