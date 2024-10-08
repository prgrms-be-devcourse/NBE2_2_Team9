package com.medinine.pillbuddy.domain.notification.dto;

import com.medinine.pillbuddy.domain.notification.entity.Notification;
import com.medinine.pillbuddy.domain.userMedication.entity.Frequency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class NotificationDTO {
    private Long notificationId;
    private String medicationName;
    private Frequency frequency;
    private LocalDateTime notificationTime;
    private Long caretakerId;

    public static List<NotificationDTO> convertToDTOs(List<Notification> notifications) {
        return notifications.stream()
                .map(notification -> NotificationDTO.builder()
                        .notificationId(notification.getId())
                        .notificationTime(notification.getNotificationTime())
                        .medicationName(notification.getUserMedication().getName())
                        .frequency(notification.getUserMedication().getFrequency())
                        .caretakerId(notification.getCaretaker().getId()).build())
                .collect(Collectors.toList());
    }

    public static NotificationDTO convertToDTO(Notification notification) {
        return NotificationDTO.builder()
                .notificationId(notification.getId())
                .notificationTime(notification.getNotificationTime())
                .medicationName(notification.getUserMedication().getName())
                .frequency(notification.getUserMedication().getFrequency())
                .caretakerId(notification.getCaretaker().getId())
                .build();
    }
}
