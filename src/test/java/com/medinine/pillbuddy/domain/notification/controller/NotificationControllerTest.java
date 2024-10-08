package com.medinine.pillbuddy.domain.notification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medinine.pillbuddy.domain.notification.provider.SmsProvider;
import com.medinine.pillbuddy.domain.notification.repository.NotificationRepository;
import com.medinine.pillbuddy.domain.notification.service.NotificationService;
import com.medinine.pillbuddy.domain.user.caretaker.repository.CaretakerCaregiverRepository;
import com.medinine.pillbuddy.domain.user.caretaker.repository.CaretakerRepository;
import com.medinine.pillbuddy.domain.userMedication.repository.UserMedicationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

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

    @Test
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
    @DisplayName("알림 조회 테스트")
    void findNotifications_test() throws Exception {
        // given
        Long caretakerId = 1L;

        // when & then
        mvc.perform(get(BASE_URL + "/user/" + caretakerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    @DisplayName("알림 수정 테스트")
    void updateNotificationTime_test() throws Exception {
        // given
        Long notificationId = 1L;
        LocalDateTime notificationTime = LocalDateTime.now().plusHours(1);

        // when & then
        mvc.perform(patch(BASE_URL + "/" + notificationId + "/" + notificationTime)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("알림 삭제 테스트")
    void deleteNotificationTime_test() throws Exception {
        // given
        Long notificationId = 1L;

        // when & then
        mvc.perform(delete(BASE_URL + "/" + notificationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("삭제되었습니다. Id: " + notificationId));
    }
}