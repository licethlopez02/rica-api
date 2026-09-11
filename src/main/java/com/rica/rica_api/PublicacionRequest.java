package com.rica.rica_api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public class PublicacionRequest {

    @NotBlank (message = "El correo del invetsigador es obligatorio")
    private String investigadorCorreo;
    @NotBlank (message = "El titulo de la publicación es obligatorio")
    private String titulo;
    @NotBlank(message = "El tipo de publicación es obligatorio")
    private String tipo;
    @NotNull
    private Integer anio;
    private Map<String, String> detalles;

    public PublicacionRequest(String investigadorCorreo, String titulo, String tipo, Integer anio, Map<String, String> detalles) {
        this.investigadorCorreo = investigadorCorreo;
        this.titulo = titulo;
        this.tipo = tipo;
        this.anio = anio;
        this.detalles = detalles;
    }

    public PublicacionRequest() {}

    public String getInvestigadorCorreo() {
        return investigadorCorreo;
    }

    public void setInvestigadorCorreo(String investigadorCorreo) {
        this.investigadorCorreo = investigadorCorreo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Map<String, String> getDetalles() {
        return detalles;
    }

    public void setDetalles(Map<String, String> detalles) {
        this.detalles = detalles;
    }
}
