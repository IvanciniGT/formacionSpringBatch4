package com.curso.jobs.job1.listeners;

import com.curso.jobs.job1.steps.step1.listeners.IProcesadorStep1Listener;
import com.curso.models.PersonaIn;
import com.curso.models.PersonaOut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("English")
public class ProcesadorStep1ListenerOtro implements IProcesadorStep1Listener {
    @Override
    public void beforeProcess(PersonaIn personaIn) {
        System.out.println("Processing person: " + personaIn);
    }

    @Override
    public void afterProcess(PersonaIn personaIn, PersonaOut personaOut) {
        System.out.println("Person processed: " + personaOut);
    }

    @Override
    public void onProcessError(PersonaIn personaIn, Exception e) {
        System.out.println("Error processing person: " + personaIn);
    }
}
