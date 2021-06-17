package com.levi9.internship.TennisScheduler.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "time_slot")
@Getter
@Setter
@NoArgsConstructor
public class TimeSlot {

    @Id
    @SequenceGenerator(name = "slot_seq_gen", sequenceName = "time_slot_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "slot_seq_gen")
    private Long id;

    @Column(name = "start_date_and_time")
    private LocalDateTime startDateAndTime;

    @Column(name = "end_date_and_time")
    private LocalDateTime endDateAndTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tennis_court_id")
    private TennisCourt tennisCourt;

    @Column(name = "deleted")
    private Boolean deleted;

}
