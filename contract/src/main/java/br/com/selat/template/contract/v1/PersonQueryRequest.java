package br.com.selat.template.contract.v1;

public class PersonQueryRequest {

    String name;
    String address;
    String birthDate;
    String email;
    Gender gender;

    public PersonQueryRequest(){}

    public PersonQueryRequest withName(String name){
        this.name = name;
        return this;
    }

    public PersonQueryRequest withAddress(String address){
        this.address = address;
        return this;
    }

    public PersonQueryRequest withBirthDate(String birthDate){
        this.birthDate = birthDate;
        return this;
    }

    public PersonQueryRequest withEmail(String email){
        this.email = email;
        return this;
    }

    public PersonQueryRequest withGender(Gender gender){
        this.gender = gender;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }
}
