package com.curso.jobs.job1.steps.step1.reader;

import com.curso.models.PersonaIn;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
// ^^ Spring, mira esta clase.. que en ella te estoy definiendo unos BEANS
public class LectorStep1 {

    // Le voy a decir que la tiene en el archivo de configuración de mi proyecto.
    // En automático, Spring, cuando arranque mi app, va a buscar en el classpath un archivo llamado application.properties
    // Y va a carga las propiedades ahí definidas
    // Del archivo application.properties, voy a leer la propiedad archivo.personas y su valor lo usas para rellanar esta variable
    @Value("${archivo.personas}")
    private String rutaFicheroPersonas; // Le inyecto a la variable rutaFicheroPersonas el valor de la propiedad archivo.personas según esté definida en application.properties

    // Spring, invoca tu a esta función...
    // Y si ves que alguien luego pide un ItemReader<PersonaIn>... pues le das lo que esta función te haya devuelto.
    @Bean                            // Función, ala que podría haber llamado Felipe!
    public ItemReader<PersonaIn> configurarMiItemReaderDelStep1(
            FieldSetMapper<PersonaIn> mapeador
    ) {
        // Esto lo podría hacer.. similar a cómo hice en IProcesadorStep1... pero
        //... pero... Seré yo la primera persona en el mundo que quiere leer un fichero excel con datos?
        // Evidentemente NO.
        // Y SpringBatch ya me da un ItemReader para leer ficheros excel.
        // Me da in ItemProcessor para procesar PersonaIn -> PersonaOut validando el DNI???
        // Pero realmente lo que me da no es un ItemReader... Es una herramienta que me permite crear ItemReaders...
        // adaptado a la estructura de mis ficheros.
        // ItemReaderBuilder... y lo cierto es que hay muchos tipos de ItemReaderBuilder... y uno de ellos es el de leer ficheros excel.
        return new FlatFileItemReaderBuilder<PersonaIn>() // Este empiezo a configurarlo para que me genere mi ItemReaderConcreto
                .name("lector ficheros personas excel") // Le pongo un nombre
                .resource(new ClassPathResource(rutaFicheroPersonas)) // Le digo de donde voy a leer los datos
                .delimited() // Le digo que los datos están separados por un delimitador
                .names("nombre", "apellido", "fechaDeNacimiento", "email", "DNI") // Le digo los nombres de las columnas
                //.targetType(PersonaIn.class)
                .fieldSetMapper( mapeador ) // Aqui vamos a poner una clase mapeadora que me convierta un FieldSet (que es lo que lee un flatFileItemReader) en un objeto de tipo PersonaIn
                .build();// Le digo
        // En este caso, en lugar de crearme yo una clase que implemente ItemReader, le digo a Spring que la cree por mi.
        // No solo la case... Sino una instancia de esa clase
    }
    // ACABADO !
    // Debería hacerle pruebas... YA HICIMOS AYER... mismo palo!
}
