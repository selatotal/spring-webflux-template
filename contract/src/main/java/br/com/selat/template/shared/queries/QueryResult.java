package br.com.selat.template.shared.queries;

import reactor.core.publisher.Flux;

public record QueryResult<T> (Flux<T> result, Long totalElements){}
