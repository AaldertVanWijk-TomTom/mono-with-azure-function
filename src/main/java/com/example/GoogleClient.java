package com.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

import static io.micronaut.http.HttpRequest.GET;

public class GoogleClient {
    @Inject
    @Client("https://www.google.com")
    HttpClient httpClient;

    public Mono<String> healthMono() {
        return Mono.from(httpClient.retrieve(
                GET("/").accept(MediaType.ALL_TYPE),
                String.class)).log();
    }
}
