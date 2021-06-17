package com.levi9.internship.TennisScheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@NoArgsConstructor
@ToString
public class Authority implements GrantedAuthority {

    @Id
    @SequenceGenerator(name = "authority_seq_gen", sequenceName = "authority_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "authority_seq_gen")
    private Long id;

    @Column(name="name")
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
