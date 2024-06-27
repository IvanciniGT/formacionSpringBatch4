package com.curso.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// ACABADO !
// Y he montado este archivo.. y no lo referencio por ningñun sitio.
// Es un COMPONENTE DESACOPLADO de mi app.
//       v
@Component
public class ValidacionesPersona implements IValidacionesPersona{

    private static final String letrasControlDNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    public boolean validaEmail(String email){
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public boolean validaFechaNacimiento(LocalDate fechaNacimiento){
        return fechaNacimiento.isBefore(LocalDate.now()); // Quiero que sena mayores de edad
    }

    public ResultadoDeLaValidacionDNI validaDNI(String dni){
        if(dni.matches("[0-9]{1,8}[A-Z]")) { // Si tiene el formato correcto
            String parteNumerica = dni.substring(0, dni.length() - 1);
            char letra = dni.charAt(dni.length() - 1);
            int numero = Integer.parseInt(parteNumerica);
            if (letrasControlDNI.charAt(numero % 23) == letra) { // Si es válido
                return new ResultadoValidacionImpl(true, numero, letra);
            } else {
                return new ResultadoValidacionImpl(false, numero, letra);
            }
        }else{
            return new ResultadoValidacionImpl(false, -1, ' ');
        }
    }

    @Data
    @AllArgsConstructor
    private static class ResultadoValidacionImpl implements ResultadoDeLaValidacionDNI {
        private final boolean esValido;
        private final int numeroDNI;
        private final char letraDNI;

        public boolean esValido() {
            return esValido;
        }
    }

}
// 1. No quiero ese código en mi componente de procesamiento de datos.
//    Es un código que puedo reusar... y que tiene otra responsabilidad.
//    Tiene sentido que esté en un componente independiente.
// 2. No quiero atar el componente ProcesadorStep1 a una implementación de este código.
//    Quizás el día de mañana hago una nueva implementación de IValidacionesPersona.
// Que haga validaciones más complejas... y no quiero tener que cambiar el ProcesadorStep1.