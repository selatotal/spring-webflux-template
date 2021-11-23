package br.com.selat.template.person.entities;

import br.com.selat.template.contract.v1.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.Instant;

@Table("PERSON")
public record PersonEntity(
        @Id
        Long id,

        @Column
        String name,

        @Column
        String address,

        @Column
        Instant birthDate,

        @Column
        String email,

        @Column
        Gender gender
){}