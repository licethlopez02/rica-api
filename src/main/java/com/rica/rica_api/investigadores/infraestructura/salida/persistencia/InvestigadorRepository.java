package com.rica.rica_api.investigadores.infraestructura.salida.persistencia;

import com.rica.rica_api.investigadores.dominio.Investigador;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestigadorRepository extends JpaRepository<Investigador, Long> {

    boolean existsByCorreoInstitucional_Valor(String valor);

    Optional<Investigador> findByCorreoInstitucional_Valor(String valor);
}
