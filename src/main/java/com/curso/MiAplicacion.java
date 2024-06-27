package com.curso;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// ^^^ Le indicamos a Spring que busque en el paquete actual y en los subpaquetes, las clases que tengan anotaciones de Spring
// El problema es que Spring no se ha enterado aún, que estoy montando una aplicación SpringBatch.
// Se lo hemos dicho por algún sitio? NO... vamos a decirselo:
@EnableBatchProcessing // Que esta es una app SpringBatch
//@ActiveProfiles("Español") // Le digo a Spring que cuando arranque mi app, cargue el perfil dev
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
    // Y el proyecto está ACABADO! (Al menos en v.1)
    // He configurado un flujo para mi aplicación en algún sitio? NO
    // He configurado un flujo para un JOB... eso si...
    // HE dicho por algún sitio que hay que ejecutar ese Job? NO
    // He dicho que el Step debe ejecutar primero el reader, luego el processor y luego el writer? NO
    // El flujo de la aplicación lo pone Spring.
    // Hombre... el flujo del JOB que quiero ejecutar.. ese si lo tengo que dar yo... PERO ESE!

}
