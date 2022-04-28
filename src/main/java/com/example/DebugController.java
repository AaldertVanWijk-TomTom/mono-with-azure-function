package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Controller("/google")
public class DebugController {

    @Inject
    GoogleClient googleClient;

    @Get("/block")
    public String block() {
        return googleClient.healthFluxReactor().blockFirst().body();
    }

    @Get("/mono")
    public Mono<String> mono() {
        return googleClient.healthMono().log();
    }

    @Get("/flux")
    public Flux<String> flux() {
        return Flux.from(googleClient.healthMono()).log();
    }

    @Get("/flux-reactive")
    public Flux<HttpResponse<String>> fluxReactive() {
        return googleClient.healthFluxReactor().log();
    }

    @Get("/mono-reactive")
    public Mono<HttpResponse<String>> monoReactive() {
        return Mono.from(googleClient.healthFluxReactor()).log();
    }

    @Get("/flux-to-mono")
    public Mono<HttpResponse<String>> fluxReactiveToMono() {
        return googleClient.healthFluxReactor().collectList().map(response -> response.stream().findFirst().orElse(null)).log();
    }

    @Get("/delayed")
    public Mono<Object> delayed() {
        return Mono.just("asdf").log().map(s -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return googleClient.healthMono().log();
        });
    }
}
