package com.rica.rica_api.investigadores.aplicacion;

import java.util.List;
import java.util.Optional;

import com.rica.rica_api.investigadores.dominio.Investigador;

public interface RepositorioInvestigadores {

    List<Investigador> listarTodos();
    Optional<Investigador> buscarPorId(Long id);
    boolean existeCorreo(String correoInstitucional);
    Investigador guardar(Investigador investigador);
    
}
