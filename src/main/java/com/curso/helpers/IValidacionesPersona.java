package com.curso.helpers;

import java.time.LocalDate;

public interface IValidacionesPersona {

    boolean validaEmail(String email);

    boolean validaFechaNacimiento(LocalDate fechaNacimiento);

    ResultadoDeLaValidacionDNI validaDNI(String dni);

    interface ResultadoDeLaValidacionDNI {
        boolean esValido();
        int getNumeroDNI();
        char getLetraDNI();
    }
}
// 1. No quiero ese código en mi componente de procesamiento de datos.
//    Es un código que puedo reusar... y que tiene otra responsabilidad.
//    Tiene sentido que esté en un componente independiente.
// 2. No quiero atar el componente ProcesadorStep1 a una implementación de este código.
//    Quizás el día de mañana hago una nueva implementación de IValidacionesPersona.
// Que haga validaciones más complejas... y no quiero tener que cambiar el ProcesadorStep1.