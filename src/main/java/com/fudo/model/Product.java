package com.fudo.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class Product {
    private UUID id;
    @NotNull
    private String name;

    public Product(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
