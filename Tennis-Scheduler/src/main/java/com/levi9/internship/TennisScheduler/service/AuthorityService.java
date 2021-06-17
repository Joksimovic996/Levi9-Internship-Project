package com.levi9.internship.TennisScheduler.service;

import com.levi9.internship.TennisScheduler.model.Authority;

import java.util.List;

public interface AuthorityService {

    List<Authority> findByName(String name);
}
