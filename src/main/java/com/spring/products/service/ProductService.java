package com.spring.products.service;

import com.spring.products.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private List<ProductEntity> productList = new ArrayList<>();

    // Constructor:
    public ProductService() {
        productList.add(new ProductEntity("Monitor", "Visual", 700.000, 50));
        productList.add(new ProductEntity("TarjetaGrafica", "Rendimiento", 900.000, 80));
        productList.add(new ProductEntity("Procesador", "Rendimiento", 700.000, 45));
    }
    
    public ProductEntity createProduct(String name, String category, double price, int stock) {
        ProductEntity newProduct = new ProductEntity(name, category, price, stock);
        productList.add(newProduct);
        return newProduct;
    }

    public List<ProductEntity> getAllProducts() {
        return productList;
    }

    public Optional<ProductEntity> getProductById(UUID id) {
        return productList.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    public Optional<ProductEntity> updateProduct(UUID id, String name, String category, double price, int stock) {
        Optional<ProductEntity> productOpt = getProductById(id);
        if (productOpt.isPresent()) {
            ProductEntity product = productOpt.get();
            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);
            product.setStock(stock);
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public boolean deleteProduct(UUID id) {
        return productList.removeIf(product -> product.getId().equals(id));
    }
}
