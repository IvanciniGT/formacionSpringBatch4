package com.curso.jobs.job1.steps.step1.listeners;

import com.curso.models.PersonaIn;
import com.curso.models.PersonaOut;
import org.springframework.batch.core.ItemProcessListener;

public interface IProcesadorStep1Listener extends ItemProcessListener<PersonaIn, PersonaOut> {
}
