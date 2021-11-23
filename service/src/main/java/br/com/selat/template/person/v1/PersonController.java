package br.com.selat.template.person.v1;

import br.com.selat.template.contract.v1.PersonInput;
import br.com.selat.template.contract.v1.PersonOutput;
import br.com.selat.template.contract.v1.PersonService;
import br.com.selat.template.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    private Mono<PersonOutput> findById(@PathVariable Long id){
        return personService
                .findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException()));
    }

    @PostMapping
    private Mono<PersonOutput> save(@RequestBody PersonInput personInput){
        return personService
                .save(personInput)
                .switchIfEmpty(Mono.error(new NotFoundException()));
    }

    @PutMapping("/{id}")
    private Mono<PersonOutput> update(@PathVariable Long id, @RequestBody PersonInput personInput){
        return personService
                .update(id, personInput)
                .switchIfEmpty(Mono.error(new NotFoundException()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private Mono<Void> remove(@PathVariable Long id){
        return personService.remove(id);
    }
}
