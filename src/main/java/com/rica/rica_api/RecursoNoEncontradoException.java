package com.rica.rica_api;

public class RecursoNoEncontradoException extends RuntimeException{

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
