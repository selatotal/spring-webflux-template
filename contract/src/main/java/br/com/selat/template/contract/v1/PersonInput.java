package br.com.selat.template.contract.v1;

public record PersonInput(
        String name,
        String address,
        Long birthDate,
        String email,
        Gender gender
){

}