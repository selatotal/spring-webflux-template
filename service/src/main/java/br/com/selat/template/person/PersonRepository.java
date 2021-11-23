package br.com.selat.template.person;

import br.com.selat.template.person.entities.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepository extends ReactiveCrudRepository<PersonEntity, Long> {
}
