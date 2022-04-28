package com.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

@MicronautTest
public class MonoTest {
    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testMono() {
        HttpRequest<String> request = HttpRequest.GET("/google/mono");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        Assertions.assertTrue(body.contains("Google"));
    }

    @Test
    void testFlux() {
        HttpRequest<String> request = HttpRequest.GET("/google/flux");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        Assertions.assertTrue(body.contains("Google"));
    }

    @Test
    void testMonoReactive() {
        HttpRequest<String> request = HttpRequest.GET("/google/mono-reactive");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        Assertions.assertTrue(body.contains("Google"));
    }

    @Test
    void testFluxReactive() {
        HttpRequest<String> request = HttpRequest.GET("/google/flux-reactive");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        Assertions.assertTrue(body.contains("Google"));
    }

    @Test
    void testFluxToMono() {
        HttpRequest<String> request = HttpRequest.GET("/google/flux-to-mono");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        Assertions.assertTrue(body.contains("Google"));
    }

    @Test
    void testBlock() {
        HttpRequest<String> request = HttpRequest.GET("/google/block");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        Assertions.assertTrue(body.contains("Google"));
    }
}

