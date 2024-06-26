package com.curso.jobs.job1.steps.step1.processor;

import com.curso.MiAplicacion;
import com.curso.helpers.IValidacionesPersona;
import com.curso.models.PersonaIn;
import com.curso.models.PersonaOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( // SpringBootTest, arranca una aplicación de Spring para poder hacer test de integración
        classes = {MiAplicacionTest.class} // Le decimos a Spring que arranque esta clase
)
@ExtendWith(SpringExtension.class) // JUnit, que sepas, que le puedes pedir cosas al Spring
class ProcesadorStep1Test {

    private ProcesadorStep1 procesadorQueVoyAProbar;

    @MockBean
    // Mockito, genera una clase dummy que implemente IValidacionesPersona
    // Es decir una clase que a todas las funciones devuelva el valor más básico posible: false, null, 0, etc.
    // Pero con esta anotación también le pedimos a Spring que si alguien necesita una implementación de IValidacionesPersona
    // Le de esta clase dummy... aunque haya otra implementación de IValidacionesPersona en el contexto.
    private IValidacionesPersona validador;

    ProcesadorStep1Test(@Autowired ProcesadorStep1 procesadorQueVoyAProbar){ // Inyección de dependencias
        this.procesadorQueVoyAProbar = procesadorQueVoyAProbar;
    }
    // Qué problemas tengo a priori?
    // - Para que se entregue la dependencia, necesito que esta clase sea instanciada por Spring.
    //    La va a instanciar Spring? NO.
    //    Quién la va a instanciar? Quién escribe 'new ProcesadorStep1Test();' ? JUNIT
    //    Y JUnit sabe que ese dato no lo tiene que entregar él y que debe suministraselo a su vez Spring? NO
    //    Pues hay que decirselo!!!! 2 cosas @Autowired y @ExtendWith(SpringExtension.class)
    // - El procesador qué necesita para funcionar? IValidacionesPersona validador
    //   Hay alguna implementación del Validador? NO
    //   Entonces no puedo probar? Y a qué espero a tener el tren montao entero?
    //   Lo que hacemos es configurar un stub de IValidacionesPersona (mock)
    //   esos 4 hierro mal soldaos.. ese sensor que metíamos en el sistema de frenos... el el mundo del testing se denemina un TEST-DOUBLE
    //   Y hay muchos tipos de test double: stubs, mocks, spies, fakes, dummies, etc.
    //   En java los creamos fácilmente con librerías como Mockito (que viene de serie en SpringBoot Starter Test)
    //   Con mockito creamos de to do: mocks, stubs, spies, etc.
    //   Pero mockito, por la coña del nombre, a to do le llama mock.
    // - Para poder pedir a Spring que nos de dependencias, tenemos que tener arrancada una aplicacion de Spring.
    //   La tengo? NO... Arranquemos una en paralelo a los test. @SpringBootTest
    @Test
    @DisplayName("Probar procesamiento de persona buena")
    void probarProcesamientoDePersonaBuena() throws Exception {
        // Configuro el escenario de la prueba       GIVEN
        PersonaIn personaIn = damePersonaBuena();
        // Configuro el mock para que devuelva siempre true en cualquiera del sus funciones
         Mockito.when(validador.validaEmail(personaIn.getEmail())).thenReturn(true);
         Mockito.when(validador.validaFechaNacimiento(personaIn.getFechaDeNacimiento())).thenReturn(true);
         Mockito.when(validador.validaDNI(personaIn.getDNI())).thenReturn(new IValidacionesPersona.ResultadoDeLaValidacionDNI() {
             @Override
             public boolean esValido() {
                 return true;
             }
             @Override
             public int getNumeroDNI() {
                 return Integer.parseInt(personaIn.getDNI().substring(0,personaIn.getDNI().length()-2));
             }
             @Override
             public char getLetraDNI() {
                 return personaIn.getDNI().charAt(personaIn.getDNI().length()-1);
             }
         });
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

    @Test
    @DisplayName("Probar procesamiento de persona con Email no válido")
    void probarProcesamientoDePersonaConEmailNoValido() throws Exception {
        // Configuro el escenario de la prueba       GIVEN
        PersonaIn personaIn = damePersonaBuena();
        // Configuro el mock para que devuelva siempre true en cualquiera del sus funciones
        Mockito.when(validador.validaEmail(personaIn.getEmail())).thenReturn(false);
        Mockito.when(validador.validaFechaNacimiento(personaIn.getFechaDeNacimiento())).thenReturn(true);
        Mockito.when(validador.validaDNI(personaIn.getDNI())).thenReturn(new IValidacionesPersona.ResultadoDeLaValidacionDNI() {
            @Override
            public boolean esValido() {
                return true;
            }
            @Override
            public int getNumeroDNI() {
                return Integer.parseInt(personaIn.getDNI().substring(0,personaIn.getDNI().length()-2));
            }
            @Override
            public char getLetraDNI() {
                return personaIn.getDNI().charAt(personaIn.getDNI().length()-1);
            }
        });
        // Ejecuto lo que quiero probar             WHEN
        PersonaOut personaOut = this.procesadorQueVoyAProbar.process((personaIn));
        // Compruebo los resultado                  THEN
        assertNull(personaOut);
    }
    @Test
    @DisplayName("Probar procesamiento de persona con Fecha de Nacimiento no válido")
    void probarProcesamientoDePersonaConFechaDeNacimientoNoValido() throws Exception {
        // Configuro el escenario de la prueba       GIVEN
        PersonaIn personaIn = damePersonaBuena();
        // Configuro el mock para que devuelva siempre true en cualquiera del sus funciones
        Mockito.when(validador.validaEmail(personaIn.getEmail())).thenReturn(true);
        Mockito.when(validador.validaFechaNacimiento(personaIn.getFechaDeNacimiento())).thenReturn(false);
        Mockito.when(validador.validaDNI(personaIn.getDNI())).thenReturn(new IValidacionesPersona.ResultadoDeLaValidacionDNI() {
            @Override
            public boolean esValido() {
                return true;
            }
            @Override
            public int getNumeroDNI() {
                return Integer.parseInt(personaIn.getDNI().substring(0,personaIn.getDNI().length()-2));
            }
            @Override
            public char getLetraDNI() {
                return personaIn.getDNI().charAt(personaIn.getDNI().length()-1);
            }
        });
        // Ejecuto lo que quiero probar             WHEN
        PersonaOut personaOut = this.procesadorQueVoyAProbar.process((personaIn));
        // Compruebo los resultado                  THEN
        assertNull(personaOut);
    }

    @Test
    @DisplayName("Probar procesamiento de persona con DNI no válido")
    void probarProcesamientoDePersonaConDniNoValido() throws Exception {
        // Configuro el escenario de la prueba       GIVEN
        PersonaIn personaIn = damePersonaBuena();
        // Configuro el mock para que devuelva siempre true en cualquiera del sus funciones
        Mockito.when(validador.validaEmail(personaIn.getEmail())).thenReturn(true);
        Mockito.when(validador.validaFechaNacimiento(personaIn.getFechaDeNacimiento())).thenReturn(true);
        Mockito.when(validador.validaDNI(personaIn.getDNI())).thenReturn(new IValidacionesPersona.ResultadoDeLaValidacionDNI() {
            @Override
            public boolean esValido() {
                return false;
            }
            @Override
            public int getNumeroDNI() {
                return Integer.parseInt(personaIn.getDNI().substring(0,personaIn.getDNI().length()-2));
            }
            @Override
            public char getLetraDNI() {
                return personaIn.getDNI().charAt(personaIn.getDNI().length()-1);
            }
        });
        // Ejecuto lo que quiero probar             WHEN
        PersonaOut personaOut = this.procesadorQueVoyAProbar.process((personaIn));
        // Compruebo los resultado                  THEN
        assertNull(personaOut);
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