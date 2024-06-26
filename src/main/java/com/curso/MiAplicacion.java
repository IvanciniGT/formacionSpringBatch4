package com.curso;

import com.curso.models.PersonaIn;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// ^^^ Le indicamos a Spring que busque en el paquete actual y en los subpaquetes, las clases que tengan anotaciones de Spring
public class MiAplicacion {

    public static void main(String[] args) {
       // AQUÍ VA EL CÓDIGO DE MI APP
        // Cuando se ejecute mi app, lo que tenga aquí es lo que se ejecuta
        // Y mi app va a tener 1 sola línea de código.
        // No digo en la curso.. digo en la realidad.. en vuestros proyectos... Mi app solo tiene 1 santa línea de código
        SpringApplication.run(MiAplicacion.class, args); // QUE CONCEPTO ES ESTE???? INVERSION DE CONTROL. Delego la ejecución de mi app a Spring.
        // Spring, a partir de este momento es quien decide el flujo de mi app.
        // YA TENGO MI APP montada.
        // Esto no es por ser un curso.. Que realmente este to do el código que voy a meter.. es lo que voy a hacer en la vida real.
        // Solo me queda explicarle a Spring en qué consiste mi app. Qué tiene? Y YA !

    }

}
