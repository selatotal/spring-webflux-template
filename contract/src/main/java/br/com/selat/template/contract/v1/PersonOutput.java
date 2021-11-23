package br.com.selat.template.contract.v1;

public record PersonOutput(
        Long id,
        String name,
        String address,
        Long birthDate,
        String email,
        Gender gender
){}