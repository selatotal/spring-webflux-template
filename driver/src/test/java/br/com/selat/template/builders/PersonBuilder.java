package br.com.selat.template.builders;

import br.com.selat.template.contract.v1.Gender;
import br.com.selat.template.contract.v1.PersonInput;

public class PersonBuilder {

    public static final String NAME = "Driver Test";
    public static final String ADDRESS = "221B, Baker Str";
    public static final Long BIRTH_DATE = 1636477560000L;
    public static final String EMAIL = "sherlock.holmes@london.uk";
    public static final Gender GENDER = Gender.MALE;

    public static PersonInput buildPersonInput(){
        return buildPersonInput(NAME);
    }

    public static PersonInput buildPersonInput(String name){
        return new PersonInput(name, ADDRESS, BIRTH_DATE, EMAIL, GENDER);
    }
}
