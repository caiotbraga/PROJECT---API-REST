package med.voll.api.controller;

import med.voll.api.domain.Enums.Specialty;
import med.voll.api.domain.consultation.dto.ScheduleConsultationData;
import med.voll.api.domain.consultation.dto.ScheduleConsultationInfo;
import med.voll.api.domain.consultation.service.ScheduleConsultationService;
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
@WithMockUser
@AutoConfigureJsonTesters
class ScheduleConsultationControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private JacksonTester<ScheduleConsultationData> jsonRequest;

  @Autowired
  private JacksonTester<ScheduleConsultationInfo> jsonResponse;

  @MockBean
  ScheduleConsultationService scheduleConsultation;

  @Test
  @DisplayName("Must return status 400 when data is invalid")
  void scheduleConsultationScenario01() throws Exception {
    var response = mvc.perform(post("/consultation/toSchedule")).andReturn().getResponse();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  @DisplayName("Must return status 200 when all data is valid")
  void scheduleConsultationScenario02() throws Exception {
    var date = LocalDateTime.now().plusHours(1);
    var specialty = Specialty.CARDIOLOGY;
    var scheduleConsultationInfo = new ScheduleConsultationInfo( 1L, 2L, specialty, date);

    when(scheduleConsultation.toSchedule(any())).thenReturn(scheduleConsultationInfo);

    var response = mvc.perform(post("/consultation/toSchedule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest.write(
            new ScheduleConsultationData(1L, 1L, specialty, date)
            ).getJson()
        )
    ).andReturn().getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    var jsonExpected = jsonResponse.write(
        scheduleConsultationInfo
    ).getJson();

    assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
  }

  @Test
  @DisplayName("Must return status 400 when data is invalid")
  void cancelattionAppointmentScenario01() throws Exception {
    var response = mvc.perform(post("/consultation/cancel")).andReturn().getResponse();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }


}
