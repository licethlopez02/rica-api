package com.rica.rica_api.investigadores.aplicacion;

import com.rica.rica_api.investigadores.dominio.Investigador;

import java.util.List;

public interface InvestigadorUseCase {
    
    List<Investigador> listarTodos();
    Investigador buscarPorId(Long id);
    Investigador registrar(String nombreCompleto, String correoInstitucional, String grupoInvestigacion);

}
