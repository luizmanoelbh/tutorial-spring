package com.luiz.cit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiz.cit.model.Vinho;

public interface Vinhos extends JpaRepository<Vinho, Long> {
}
