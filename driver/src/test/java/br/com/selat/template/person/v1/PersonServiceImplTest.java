package br.com.selat.template.person.v1;

import br.com.selat.template.TemplateServiceDriver;
import br.com.selat.template.builders.PersonBuilder;
import br.com.selat.template.contract.v1.PersonInput;
import br.com.selat.template.contract.v1.PersonOutput;
import br.com.selat.template.contract.v1.PersonService;
import br.com.selat.template.shared.exceptions.NotFoundException;
import br.com.selat.template.shared.exceptions.ServiceValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

import static br.com.selat.template.builders.PersonBuilder.*;

class PersonServiceImplTest {

    private final PersonService service = TemplateServiceDriver.buildPersonService();

    @Test
    void shouldCreatePerson(){
        PersonInput input = PersonBuilder.buildPersonInput();
        Mono<PersonOutput> result = service.save(input);
        Optional<PersonOutput> optional = result.blockOptional();
        Assertions.assertTrue(optional.isPresent());
        PersonOutput output = optional.get();
        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());
        Assertions.assertEquals(NAME, output.name());
        Assertions.assertEquals(ADDRESS, output.address());
        Assertions.assertEquals(BIRTH_DATE, output.birthDate());
        Assertions.assertEquals(EMAIL, output.email());
        Assertions.assertEquals(GENDER, output.gender());
        service.remove(output.id()).block();
    }

    @Test
    void shouldUpdatePerson(){
        PersonInput input = PersonBuilder.buildPersonInput();
        Mono<PersonOutput> result = service.save(input);
        Optional<PersonOutput> optional = result.blockOptional();
        Assertions.assertTrue(optional.isPresent());
        PersonOutput output = optional.get();
        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        PersonInput inputUpdate = PersonBuilder.buildPersonInput("New Name");
        result = service.update(output.id(), inputUpdate);
        optional = result.blockOptional();
        Assertions.assertTrue(optional.isPresent());
        PersonOutput outputUpdate = optional.get();

        Assertions.assertEquals(output.id(), outputUpdate.id());
        Assertions.assertEquals("New Name", outputUpdate.name());
        Assertions.assertEquals(ADDRESS, outputUpdate.address());
        Assertions.assertEquals(BIRTH_DATE, outputUpdate.birthDate());
        Assertions.assertEquals(EMAIL, outputUpdate.email());
        Assertions.assertEquals(GENDER, outputUpdate.gender());
        service.remove(outputUpdate.id()).block();
    }

    @Test
    void shouldFindPersonById(){
        PersonInput input = PersonBuilder.buildPersonInput();
        Mono<PersonOutput> result = service.save(input);
        Optional<PersonOutput> optional = result.blockOptional();
        Assertions.assertTrue(optional.isPresent());
        PersonOutput output = optional.get();
        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        result = service.findById(output.id());
        optional = result.blockOptional();
        Assertions.assertTrue(optional.isPresent());
        PersonOutput outputFind = optional.get();

        Assertions.assertEquals(output.id(), outputFind.id());
        Assertions.assertEquals(NAME, outputFind.name());
        Assertions.assertEquals(ADDRESS, outputFind.address());
        Assertions.assertEquals(BIRTH_DATE, outputFind.birthDate());
        Assertions.assertEquals(EMAIL, outputFind.email());
        Assertions.assertEquals(GENDER, outputFind.gender());
        service.remove(outputFind.id()).block();
    }

    @Test
    void shouldRemove(){
        PersonInput input = PersonBuilder.buildPersonInput();
        Mono<PersonOutput> result = service.save(input);
        Optional<PersonOutput> optional = result.blockOptional();
        Assertions.assertTrue(optional.isPresent());
        PersonOutput output = optional.get();
        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Mono<Void> resultRemove = service.remove(output.id());
        resultRemove.block();

        Long id = output.id();
        StepVerifier.create(service.findById(id)).expectError(NotFoundException.class);
    }
}
