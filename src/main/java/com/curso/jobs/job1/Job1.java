package com.curso.jobs.job1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Y esto va a ser igual que el step.. CALCAO'!
@Configuration
public class Job1 {

    @Bean
    public Job configurarMiJob1(
            JobBuilderFactory fabricaDeJobs, // Spring me lo va a pasar
            Step unStep // Spring mirará en mi código si hay configurada de alguna forma la forma de satisfacer esta dependencia
    ) { // Y voy a aprovechar que Spriong es quien llama a esta función (por meterle lo del @Bean
                                   // Para pedirle cositas
        return fabricaDeJobs.get("job1") // Le pongo un nombre
                .flow(unStep) // Le digo que empiece con unStep
                .end() // ... y que acabe... AQUI SI PODRÍA PONER MAS STEPS (En serie, en paralelo, etc) Y SI SERIA IMPORTANTE EL ORDEN
                .build(); // Y me lo construyes un Job.... que es el que quiero que haya disponible... para cuando alguien pida un objeto de tipo Job.
    } // Y ACABADO !
}
