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
    public void testMono() {
        HttpRequest<String> request = HttpRequest.GET("/google/mono");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        Assertions.assertTrue(body.contains("Google"));
    }

    @Test
    public void testBlock() {
        HttpRequest<String> request = HttpRequest.GET("/google/block");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        Assertions.assertTrue(body.contains("Google"));
    }
}

