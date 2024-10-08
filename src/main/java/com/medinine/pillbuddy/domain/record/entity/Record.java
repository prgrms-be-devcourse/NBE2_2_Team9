package com.medinine.pillbuddy.domain.record.entity;

import com.medinine.pillbuddy.domain.userMedication.entity.UserMedication;
import com.medinine.pillbuddy.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "record")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "taken", nullable = false)
    private Taken taken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_medication_id")
    private UserMedication userMedication;

    public void changeUserMedication(UserMedication userMedication) {
        this.userMedication = userMedication;
        userMedication.getRecords().add(this);
    }

    public void takeMedication() {
        this.taken = Taken.TAKEN;
    }
}
