package com.fudo.service;

import com.fudo.model.Product;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    @Async
    public CompletableFuture<Void> addProduct(Product product) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        products.add(new Product(UUID.randomUUID(), product.getName()));
        return CompletableFuture.completedFuture(null);
    }

    public List<Product> getProducts() {
        return products;
    }
}
