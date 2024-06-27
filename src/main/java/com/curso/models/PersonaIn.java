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
@NoArgsConstructor // PAra SpringBAtch
@AllArgsConstructor // Para el patrón builder... que me ha venido guay para el test
public class PersonaIn {
    private String nombre;
    private String apellido;
    private String email;
    private String DNI;
    private LocalDate fechaDeNacimiento;
    // En el fichero viene un String... pero yo quiero un LocalDate
    // Podría cambiar aqui en entrada el dato a un String... y en procesamiento (Processor) convertirlo a LocalDate
    // Y ESO SERIA LO QUE TENDRIA MAS SENTIDO DEL MUNDO

    // Pero en este caso, para enseñaros otra cosa, lo haré de otra forma

    // Date??? java.util.Date y java.sql.Date   \
    // Calendar?? GregorianCalendar             / MIERDA TO DO de nuevo...
    // Menos mal que en JAVA 8 metieron por fin un buen reemplazo a to do ese follonazo de clases de mierda que había:
    // Básicamente incorporaron la antigua librería: JODA TIME dentro del API DE JAVA, que es guay!
    // ZonedDateTime
    // LocalDateTime
    // LocalDate
}
