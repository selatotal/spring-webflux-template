package br.com.selat.template.contract.v1;

import br.com.selat.template.shared.queries.QueryResult;
import reactor.core.publisher.Mono;

public interface PersonService {

    Mono<PersonOutput> findById(Long id);
    Mono<PersonOutput> save(PersonInput personInput);
    Mono<PersonOutput> update(Long id, PersonInput personInput);
    Mono<Void> remove(Long id);
    Mono<QueryResult<PersonOutput>> query(PersonQueryRequest personQueryRequest);

}
