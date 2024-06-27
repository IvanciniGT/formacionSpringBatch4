package com.curso.jobs.job1.mappers;

import com.curso.models.PersonaIn;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class MapeadorDePersonaIn extends BeanWrapperFieldSetMapper<PersonaIn> {
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public MapeadorDePersonaIn(){
        setTargetType(PersonaIn.class);
    }

    @Override
    public PersonaIn mapFieldSet(FieldSet fieldSet) {
        return PersonaIn.builder()
                .nombre(fieldSet.readString("nombre"))
                .apellido(fieldSet.readString("apellido"))
                .email(fieldSet.readString("email"))
                .DNI(fieldSet.readString("DNI"))
                .fechaDeNacimiento(LocalDate.parse(fieldSet.readString("fechaDeNacimiento"), FORMATO_FECHA))
                // Ya le cuento yo como hacer esa conversión de tipos
                .build();
    }
}
