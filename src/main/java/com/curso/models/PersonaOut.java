package com.curso.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PersonaOut {
    private String nombre;
    private String apellido;
    private String email;
    private int numeroDNI;
    private char letraDNI;
    private LocalDate fechaDeNacimiento;
}
