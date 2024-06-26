package com.curso.jobs.job1.steps.step1.processor;

import com.curso.models.PersonaIn;
import com.curso.models.PersonaOut;
import org.springframework.batch.item.ItemProcessor;

// Tengo que decir, que recibo y que devuelvo!
// Le cuento a SpringBatch que voy a tener en mi código un ItemProcessor especializado en PersonaIn -> PersonaOut
public interface IProcesadorStep1 extends ItemProcessor<PersonaIn, PersonaOut> {
}
