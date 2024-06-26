package com.curso.jobs.job1.steps.step1.listeners;

import com.curso.models.PersonaIn;
import com.curso.models.PersonaOut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Primary    Salvo para cosas de prueba y eso... NADA
@Profile("Español") // PROD / PRUEBAS
public class ProcesadorStep1Listener implements IProcesadorStep1Listener {
    @Override
    public void beforeProcess(PersonaIn personaIn) {
        System.out.println("Procesando persona: " + personaIn);
    }

    @Override
    public void afterProcess(PersonaIn personaIn, PersonaOut personaOut) {
        System.out.println("Persona procesada: " + personaOut);
    }

    @Override
    public void onProcessError(PersonaIn personaIn, Exception e) {
        System.out.println("Error procesando persona: " + personaIn);
    }
}
