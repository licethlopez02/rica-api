package com.rica.rica_api.investigadores.dominio;

public class CorreoDuplicadoException extends RuntimeException{

    public CorreoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
