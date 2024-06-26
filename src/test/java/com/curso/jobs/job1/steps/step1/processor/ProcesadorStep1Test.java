package com.curso.jobs.job1.steps.step1.processor;

import com.curso.models.PersonaIn;
import com.curso.models.PersonaOut;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProcesadorStep1Test {

    private ProcesadorStep1 procesadorQueVoyAProbar;

    ProcesadorStep1Test(ProcesadorStep1 procesadorQueVoyAProbar){ // Inyección de dependencias
        this.procesadorQueVoyAProbar = procesadorQueVoyAProbar;
    }

    @Test
    void process() throws Exception {
        // Configuro el escenario de la prueba       GIVEN
        PersonaIn personaIn = damePersonaBuena();
        // Ejecuto lo que quiero probar             WHEN
        PersonaOut personaOut = this.procesadorQueVoyAProbar.process((personaIn));
        // Compruebo los resultado                  THEN
        assertNotNull(personaOut);
        assertEquals(personaIn.getNombre(), personaOut.getNombre());
        assertEquals(personaIn.getApellido(), personaOut.getApellido());
        assertEquals(personaIn.getEmail(), personaOut.getEmail());
        assertEquals(personaIn.getFechaDeNacimiento(), personaOut.getFechaDeNacimiento());
        assertEquals(Integer.parseInt(personaIn.getDNI().substring(0,personaIn.getDNI().length()-2)), personaOut.getNumeroDNI());
        assertEquals(personaIn.getDNI().charAt(personaIn.getDNI().length()-1), personaOut.getLetraDNI());
        // Y si to do esto ocurre, GENIAL ! Mi programa funciona!
    }

    private PersonaIn damePersonaBuena(){
        return PersonaIn.builder()
                .nombre("Pepe")
                .apellido("Perez")
                .email("pepe@pepe.com")
                .DNI("12345678Z")
                .fechaDeNacimiento(LocalDate.of(1990, 1, 1))
                .build();
    }
}