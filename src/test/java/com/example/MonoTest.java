package com.example;

import com.microsoft.azure.functions.HttpStatus;
import io.micronaut.azure.function.http.HttpRequestMessageBuilder;
import io.micronaut.http.HttpMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonoTest {
    private final Function function = new Function();

    @Test
    void testBlock() {
        HttpRequestMessageBuilder.AzureHttpResponseMessage response =
                function.request(HttpMethod.GET, "/google/block").invoke();

        assertEquals(HttpStatus.OK, response.getStatus());
        String body = response.getBodyAsString();
        assertNotNull(body);
    }

    @Test
    void testMono() {
        HttpRequestMessageBuilder.AzureHttpResponseMessage response =
                function.request(HttpMethod.GET, "/google/mono").invoke();

        assertEquals(HttpStatus.OK, response.getStatus());
        String body = response.getBodyAsString();
        assertNotNull(body);
    }
}
