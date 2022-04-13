package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Controller("/google")
public class DebugController {

    @Inject
    GoogleClient googleClient;

    @Get("/block")
    public String block(){
        return googleClient.healthMono().block();
    }

    @Get("/mono")
    public Mono<String> mono(){
        return googleClient.healthMono().log();
    }

    @Get("/delayed")
    public Mono<Object> delayed(){
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
