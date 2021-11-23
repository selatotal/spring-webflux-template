package br.com.selat.template.person;

import br.com.selat.template.contract.v1.*;
import br.com.selat.template.person.entities.PersonEntity;
import br.com.selat.template.shared.exceptions.InternalErrorException;
import br.com.selat.template.shared.exceptions.NotFoundException;
import br.com.selat.template.shared.queries.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Mono<PersonOutput> findById(Long id) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException()))
                .map(this::toPersonOutput);
    }

    @Override
    public Mono<PersonOutput> save(PersonInput personInput) {
        return personRepository
                .save(toPersonEntity(null, personInput))
                .onErrorMap(InternalErrorException::new)
                .map(this::toPersonOutput);
    }

    @Override
    public Mono<PersonOutput> update(Long id, PersonInput personInput) {
        return personRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException()))
                .flatMap(p -> personRepository.save(toPersonEntity(id, personInput))
                        .onErrorMap(InternalErrorException::new)
                        .map(this::toPersonOutput));

    }

    @Override
    public Mono<Void> remove(Long id) {
        return personRepository
            .findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException()))
            .flatMap(p -> personRepository.deleteById(id).onErrorMap(InternalErrorException::new))
            .then();
    }

    @Override
    public Mono<QueryResult<PersonOutput>> query(PersonQueryRequest personQueryRequest) {
        return null;
    }

    private PersonOutput toPersonOutput(PersonEntity personInput) {
        if (personInput == null){
            return null;
        }
        return new PersonOutput(personInput.id(),
                personInput.name(),
                personInput.address(),
                personInput.birthDate() != null ? personInput.birthDate().toEpochMilli() : null,
                personInput.email(),
                personInput.gender());
    }

    private PersonEntity toPersonEntity(Long id, PersonInput personInput){
        return new PersonEntity(id,
                personInput.name(),
                personInput.address(),
                personInput.birthDate() != null ? Instant.ofEpochMilli(personInput.birthDate()) : null,
                personInput.email(),
                personInput.gender());
    }
}
