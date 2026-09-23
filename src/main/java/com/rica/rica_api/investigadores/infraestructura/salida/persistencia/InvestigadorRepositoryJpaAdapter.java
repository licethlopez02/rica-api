package com.rica.rica_api.investigadores.infraestructura.salida.persistencia;


import com.rica.rica_api.investigadores.aplicacion.RepositorioInvestigadores;
import com.rica.rica_api.investigadores.dominio.Investigador;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InvestigadorRepositoryJpaAdapter implements RepositorioInvestigadores{
    
    private final InvestigadorRepository investigadorRepository;

    public InvestigadorRepositoryJpaAdapter(InvestigadorRepository investigadorRepository) {
        this.investigadorRepository = investigadorRepository;
    }

    public List<Investigador> listarTodos() {
        return investigadorRepository.findAll();
    }

    public Optional<Investigador> buscarPorId(Long id) {
        return investigadorRepository.findById(id);
    }

    public boolean existeCorreo(String correoInstitucional) {
        return investigadorRepository.existsByCorreoInstitucional_Valor(correoInstitucional);
    }

    public Investigador guardar(Investigador investigador) {
        return investigadorRepository.save(investigador);
    }
}
