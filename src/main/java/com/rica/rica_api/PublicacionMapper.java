package com.rica.rica_api;

public class PublicacionMapper {
    private PublicacionMapper() {
    }

    public static Publicacion aEntidad(PublicacionRequest request) {
        Publicacion publicacion = new Publicacion();
        publicacion.setAnio(request.getAnio());
        publicacion.setDetalles(request.getDetalles());
        publicacion.setTipo(request.getTipo());
        publicacion.setTitulo(request.getTitulo());
        publicacion.setInvestigadorCorreo(request.getInvestigadorCorreo());
        return publicacion;
    }

    public static PublicacionResponse aResponse(Publicacion publicacion) {
        return new PublicacionResponse(
                publicacion.getInvestigadorCorreo(),
                publicacion.getTitulo(),
                publicacion.getTipo(),
                publicacion.getAnio(),
                publicacion.getDetalles()
        );
    }
}
