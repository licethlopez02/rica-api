package com.rica.rica_api.plataforma;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ArranqueInformativo implements CommandLineRunner {

    private final SaludoInstitucionalService saludoInstitucionalService;

    public ArranqueInformativo(SaludoInstitucionalService saludoInstitucionalService){
        this.saludoInstitucionalService = saludoInstitucionalService;
    }

    public void run(String ... args) {
        System.out.println(saludoInstitucionalService.mensajeDeBienvenida());
    }
}
