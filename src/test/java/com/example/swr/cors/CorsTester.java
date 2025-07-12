package com.example.swr.cors;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.InetSocketAddress;

/**
 * Run from CLI: java CorsTester.java
 */
public class CorsTester {
    private static final int DEFAULT_PORT = 4200;

    /**
     * @param args expect the port on which run, defaulted to DEFAULT_PORT
     * @throws IOException              for I/O errors on HTTP server
     * @throws NumberFormatException    when the passed port is not an integer
     * @throws IllegalArgumentException for an "out of range" port
     */
    public static void main(String[] args) throws IOException {
        int port = (args.length == 0) ? DEFAULT_PORT : Integer.parseInt(args[0]);
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                byte[] bytes = Files.readAllBytes(Paths.get("cors-tester.html"));
                exchange.getResponseHeaders().add("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, bytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
            }
        });

        server.start();
        System.out.println("Server started at http://localhost:" + port);
    }
}
