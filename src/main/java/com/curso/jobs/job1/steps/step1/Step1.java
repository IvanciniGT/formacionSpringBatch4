package com.curso.jobs.job1.steps.step1;

import com.curso.jobs.job1.steps.step1.processor.IProcesadorStep1;
import com.curso.jobs.job1.steps.step1.writer.IEscritorStep1;
import com.curso.models.PersonaIn;
import com.curso.models.PersonaOut;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Step1 {
    // Aqui es donde diremos que queremos usar como procesador para este step un IProcesadorStep1
    // Spring dará (buscará) la implementación que encuentre
    // Y con el STEP... me va a pasar algo parecido a lo que me pasaba con el reader
    // Yo no voy a crear una clase que extienda de Step.
    // Voy a pedir a Spring que él me cree un Step
    @Bean
    public Step configurarElStep1DeMiJob1(
            StepBuilderFactory fabricaDeSteps, // Spring me lo va a pasar
            ItemReader<PersonaIn> lectorDePersonas,// Y Spring mirará en mi código si hay configurada de alguna forma la forma de satisfacer esta dependencia
            IProcesadorStep1 procesadorDePersonas, // Spring mirará en mi código si hay configurada de alguna forma la forma de satisfacer esta dependencia
            IEscritorStep1 escritorDePersonas // Spring mirará en mi código si hay configurada de alguna forma la forma de satisfacer esta dependencia
    ) { // La podría llamar como quisiera
        // Me voy a aprovechar de que Spring es Quien va a llamar a esta función...
        // Para pedirle cositas. Solicitar una inyección de dependencias.
        return fabricaDeSteps.get("step1") // Le pongo un nombre
                .<PersonaIn, PersonaOut>chunk(10) // Le digo que escribiendo de 10 en 10
                .writer(escritorDePersonas) // Le digo que escriba con escritorDePersonas
                .processor(procesadorDePersonas) // Le digo que procese con procesadorDePersonas
                .reader(lectorDePersonas) // Le digo que lea de lectorDePersonas
                .build(); // Y me lo construyes un Step.... que es el que quiero que haya disponible... para cuando alguien pida un objeto de tipo Step.
    }// ESTO ESTA ACABADO!
    // La gracia de Spring es:
    // Por algún sitio estoy aquí referenciando al fichero/clase ProcesadorStep1 ????? NO
    // Solo a la interfaz. COMPONENTES DESACOPLADOS = SOFTWARE MUY MANTENIBLE !
    // Y lo mismo con el reader y el writer.
    // Mis componentes solo tienen relación a través de las interfaces = GENIAL !
    // Esto es usar Spring... Si no hago esto, para QUE OSTIAS USO SPRING !!!! ESTO ES SPRING !

}
