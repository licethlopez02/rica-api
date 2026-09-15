package com.rica.rica_api.publicaciones;

import com.rica.rica_api.compartido.LimiteAnualExcedidoException;
import com.rica.rica_api.compartido.RecursoNoEncontradoException;
import com.rica.rica_api.investigadores.Investigador;
import com.rica.rica_api.investigadores.InvestigadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionService {
    private final PublicacionRepository publicacionRepository;
    private final InvestigadorRepository investigadorRepository;
    private final LimitePublicacionesAnualesService limitePublicacionesAnualesService;

    public PublicacionService(PublicacionRepository publicacionRepository,
                              InvestigadorRepository investigadorRepository,
                              LimitePublicacionesAnualesService limitePublicacionesAnualesService)
    {
        this.publicacionRepository = publicacionRepository;
        this.investigadorRepository = investigadorRepository;
        this.limitePublicacionesAnualesService = limitePublicacionesAnualesService;
    }

    public Publicacion registrar(Publicacion publicacion) {
        Investigador investigador = investigadorRepository
            .findByCorreoInstitucional_Valor(publicacion.getInvestigadorCorreo())
            .orElseThrow(() -> new RecursoNoEncontradoException(
                "No existe un investigador con correo " + publicacion.getInvestigadorCorreo()));

        if (!limitePublicacionesAnualesService.puedeRegistrar(investigador, publicacion)) {
            throw new LimiteAnualExcedidoException(
                    "El investigador ha superado el límite máximo de publicaciones para el año " + publicacion.getAnio());
        }

        return publicacionRepository.save(publicacion);
    }

    public List<Publicacion> listarPorInvestigador(String investigadorCorreo) {
        return publicacionRepository.findByInvestigadorCorreo(investigadorCorreo);
    }

    public Publicacion buscarPorId(String id) {
        return publicacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe una publicación con id " + id));
    }
}
