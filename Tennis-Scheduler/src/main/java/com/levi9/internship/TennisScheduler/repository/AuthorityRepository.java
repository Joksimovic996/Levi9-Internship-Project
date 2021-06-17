package com.levi9.internship.TennisScheduler.repository;

import com.levi9.internship.TennisScheduler.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Authority findByName(String name);
}
