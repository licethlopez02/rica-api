package com.rica.rica_api.investigadores;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestigadorRepository extends JpaRepository<Investigador, Long> {

    boolean existsByCorreoInstitucional_Valor(String valor);

    Optional<Investigador> findByCorreoInstitucional_Valor(String valor);
}
