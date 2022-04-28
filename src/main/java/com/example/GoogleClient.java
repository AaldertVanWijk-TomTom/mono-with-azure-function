package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.reactor.http.client.ReactorHttpClient;
import jakarta.inject.Inject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static io.micronaut.http.HttpRequest.GET;

public class GoogleClient {
    @Inject
    @Client("https://www.google.com")
    ReactorHttpClient reactorHttpClient;

    @Inject
    @Client("https://www.google.com")
    HttpClient httpClient;

    public Mono<String> healthMono() {
        return Mono.from(httpClient.retrieve(
                GET("/").accept(MediaType.ALL_TYPE),
                String.class)).log();
    }
    public Flux<HttpResponse<String>> healthFluxReactor() {
        return reactorHttpClient.exchange(GET("/").accept(MediaType.ALL_TYPE), String.class);
    }
}
