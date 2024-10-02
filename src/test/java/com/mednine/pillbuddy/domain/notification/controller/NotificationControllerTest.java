package com.mednine.pillbuddy.domain.notification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mednine.pillbuddy.domain.medicationApi.repository.MedicationApiRepository;
import com.mednine.pillbuddy.domain.medicationApi.service.MedicationApiService;
import com.mednine.pillbuddy.domain.notification.dto.UserNotificationDTO;
import com.mednine.pillbuddy.domain.notification.provider.SmsProvider;
import com.mednine.pillbuddy.domain.notification.repository.NotificationRepository;
import com.mednine.pillbuddy.domain.notification.service.NotificationService;
import com.mednine.pillbuddy.domain.user.caretaker.repository.CaretakerCaregiverRepository;
import com.mednine.pillbuddy.domain.user.caretaker.repository.CaretakerRepository;
import com.mednine.pillbuddy.domain.userMedication.entity.Frequency;
import com.mednine.pillbuddy.domain.userMedication.repository.UserMedicationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private NotificationRepository notificationRepository;

    @MockBean
    private UserMedicationRepository userMedicationRepository;

    @MockBean
    private CaretakerCaregiverRepository caretakerCaregiverRepository;

    @MockBean
    private SmsProvider smsProvider;

    @MockBean
    private CaretakerRepository caretakerRepository;

    private static final String BASE_URL = "/api/notification";

/*    @Test
    @DisplayName("알림 생성 테스트")
    void createNotifications_test() throws Exception {
        // given
        Long userMedicationId = 1L;

        // when & then
        mvc.perform(post(BASE_URL + "/" + userMedicationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    @DisplayName("Caretaker ID로 알림을 조회할 수 있어야 한다.")
    void findNotifications_test() throws Exception {
        // given
        Long caretakerId = 1L;

        // when & then
        mvc.perform(get(BASE_URL + "/user/" + caretakerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }*/
}