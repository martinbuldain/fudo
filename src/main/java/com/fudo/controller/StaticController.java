package com.fudo.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

@RestController
public class StaticController {

    @GetMapping(value = "/openapi.yaml", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getOpenApi() throws IOException {
        byte[] content = Files.readAllBytes(new ClassPathResource("static/openapi.yaml").getFile().toPath());
        return ResponseEntity.ok()
                .cacheControl(CacheControl.noCache())
                .body(content);
    }

    @GetMapping(value = "/AUTHORS", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<byte[]> getAuthors() throws IOException {
        byte[] content = Files.readAllBytes(new ClassPathResource("static/AUTHORS").getFile().toPath());
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(24, TimeUnit.HOURS))
                .body(content);
    }
}
