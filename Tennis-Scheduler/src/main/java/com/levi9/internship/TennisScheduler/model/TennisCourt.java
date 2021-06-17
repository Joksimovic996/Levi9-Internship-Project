package com.levi9.internship.TennisScheduler.model;

import com.levi9.internship.TennisScheduler.enumerations.TennisCourtType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TennisCourt {

    @Id
    @SequenceGenerator(name = "court_seq_gen", sequenceName = "tennis_court_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "court_seq_gen")
    private Long id;

    @Column(
            name = "tennis_court_name",
            unique = true,
            nullable = false
    )
    private String name;

    @Column (name = "court_type")
    @Enumerated(EnumType.STRING)
    private TennisCourtType courtType;

    @Column (name = "price_per_minute")
    private Double pricePerMinute;

    @Column(name = "deleted")
    private Boolean deleted;

}
