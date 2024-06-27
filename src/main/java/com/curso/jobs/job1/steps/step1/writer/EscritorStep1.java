package com.curso.jobs.job1.steps.step1.writer;

import com.curso.models.PersonaOut;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EscritorStep1 implements IEscritorStep1 {
    @Override
    public void write(List<? extends PersonaOut> list) throws Exception {
        // Aqui hay algo importante.
        // El reader, cuando lee, devuelve 1 persona (UN UNICO ITEM)
        // El processor, cuando procesa, procesa persona a persona (UN UNICO ITEM)
        // Los writer, cuando escriben, escriben lotes de personas
        // Por ahora, solo los sacamos por pantalla
        list.forEach(System.out::println);
    }
}
// ACABADO !
