package org.rybina.server;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.net.http.HttpRequest.BodyPublishers.ofFile;
import static java.net.http.HttpRequest.BodyPublishers.ofString;

public class HttpClientRunner {

    public static void main(String[] args) {
        var httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000"))
                .header("content-type", "application/json")
                .POST(ofString("Your JSON body here"))
                .build();

        CompletableFuture<HttpResponse<String>> response1 = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<HttpResponse<String>> response2 = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<HttpResponse<String>> response3 = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        CompletableFuture.allOf(response1, response2, response3).join(); // Дождаться завершения всех запросов

        // Обработать ответы
        try {
            System.out.println("Response 1 body: " + response1.get().body());
            System.out.println("Response 2 body: " + response2.get().body());
            System.out.println("Response 3 body: " + response3.get().body());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(); // Обработка ошибок
        }
    }
}