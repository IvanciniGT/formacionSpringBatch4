package com.curso.models;

import lombok.*;

import java.time.LocalDate;

@Data
/*
@Getter @Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
*/
@Builder
public class PersonaIn {
    private String nombre;
    private String apellido;
    private String email;
    private String DNI;
    private LocalDate fechaDeNacimiento;
    // Date??? java.util.Date y java.sql.Date   \
    // Calendar?? GregorianCalendar             / MIERDA TO DO de nuevo...
    // Menos mal que en JAVA 8 metieron por fin un buen reemplazo a to do ese follonazo de clases de mierda que había:
    // Básicamente incorporaron la antigua librería: JODA TIME dentro del API DE JAVA, que es guay!
    // ZonedDateTime
    // LocalDateTime
    // LocalDate
}
