package br.com.selat.template.person.v1;

import br.com.selat.template.contract.v1.PersonInput;
import br.com.selat.template.contract.v1.PersonOutput;
import br.com.selat.template.contract.v1.PersonQueryRequest;
import br.com.selat.template.contract.v1.PersonService;
import br.com.selat.template.shared.exceptions.NotFoundException;
import br.com.selat.template.shared.exceptions.ServiceValidationException;
import br.com.selat.template.shared.queries.QueryResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class PersonServiceImpl implements PersonService {

    private final WebClient webClient;

    public PersonServiceImpl() {
        String baseUrl = Optional.ofNullable(System.getenv("PERSON_SERVICE_URL")).orElse(System.getProperty("person-service.url", ""));
        webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    @Override
    public Mono<PersonOutput> findById(Long id) {
        return this.webClient.get().uri("/v1/person/{id}", id).retrieve().onStatus(HttpStatus::isError, this::handleResponse).bodyToMono(PersonOutput.class);
    }

    @Override
    public Mono<PersonOutput> save(PersonInput personInput) {
        return this.webClient.post().uri("/v1/person").body(Mono.just(personInput), PersonInput.class).accept(MediaType.APPLICATION_JSON).retrieve().onStatus(HttpStatus::isError, this::handleResponse).bodyToMono(PersonOutput.class);
    }

    @Override
    public Mono<PersonOutput> update(Long id, PersonInput personInput) {
        return this.webClient.put().uri("/v1/person/{id}", id).body(Mono.just(personInput), PersonInput.class).accept(MediaType.APPLICATION_JSON).retrieve().onStatus(HttpStatus::isError, this::handleResponse).bodyToMono(PersonOutput.class);
    }

    @Override
    public Mono<Void> remove(Long id) {
        return this.webClient.delete().uri("/v1/person/{id}", id).retrieve().onStatus(HttpStatus::isError, this::handleResponse).toBodilessEntity().then();
    }

    @Override
    public Mono<QueryResult<PersonOutput>> query(PersonQueryRequest personQueryRequest) {
        return null;
    }

    private Mono<Throwable> handleResponse(ClientResponse clientResponse){
        switch (clientResponse.statusCode()){
            case NOT_FOUND -> { return Mono.error(NotFoundException::new); }
            case BAD_REQUEST -> { return Mono.error(ServiceValidationException::new); }
            default -> { return Mono.error(InternalError::new); }
        }
    }
}
