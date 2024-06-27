package com.curso.jobs.job1.steps.step1.processor;

import com.curso.helpers.IValidacionesPersona;
import com.curso.models.PersonaIn;
import com.curso.models.PersonaOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
// Spring, crea una instancia de esta clase... y si alguien me pide un IProcesadorStep1... se la das.
public class ProcesadorStep1 implements IProcesadorStep1 {

    private final IValidacionesPersona validador;

    /*
    Este código me lo da la anotación.
    public ProcesadorStep1(IValidacionesPersona validador) {
                            // Solicito una implementación de IValidacionesPersona
                            // Solicito a Spring una inyección de dependencias.
        this.validador = validador;
    }*/

    @Override
    public PersonaOut process(PersonaIn personaIn) throws Exception {
        // Qué hacemos?
        // Validar el email
        if(!validador.validaEmail(personaIn.getEmail())) {
            return null; // Al devolver null, SpringBatch entiende que no se ha podido procesar el registro, y lo elimina.
        }
        // Validar que la fecha de nacimiento no sea futura
        if(!validador.validaFechaNacimiento(personaIn.getFechaDeNacimiento())) {
            return null;
        }
        // Validar que el DNI sea correcto
        IValidacionesPersona.ResultadoDeLaValidacionDNI resultado = validador.validaDNI(personaIn.getDNI());
        if(!resultado.esValido()) {
            return null;
        }
        // Si to do ha ido bien, devolvemos la persona: PersonaOut
        return PersonaOut.builder()
                .nombre(personaIn.getNombre())
                .apellido(personaIn.getApellido())
                .email(personaIn.getEmail())
                .fechaDeNacimiento(personaIn.getFechaDeNacimiento())
                .numeroDNI(resultado.getNumeroDNI())
                .letraDNI(resultado.getLetraDNI())
                .build();
    }
    // Tengo un primer componente de mi app.. que le indica a SpringBatch cómo voy a procesar los datos.
    // Esa es la única responsabilidad de este componente.
    // Aquí con tener un IValidacionesPersona me vale... Me importa una mierda quien lo implemente???
    // Tengo ya una implementación creada de IValidacionesPersona???? NO
    // ME importa para este código? NO... yo estoy bien.
    // El día que haya una, tengo que cambiar ni siquiera una sola linea de código de este fichero? NO
    // ESTO ES UN COMPONENTE DESACOPLADO de otros componentes. ESTO ES LO QUE ME DA UN CODIGO FLEXIBLE, FACIL DE MAINTENER Y DE TESTEAR.
    // ESTO ES LO QUE OFRECE SPRING.
    // Si no uso estas cosas... para que cojones estoy montando el proyecto con Spring!
}
