package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Warehouse {
    private static final Map<String, Warehouse> INSTANCES = new ConcurrentHashMap<>();
    private final List<Product> products = new ArrayList<>();
    private final Set<Product> changedProducts = new HashSet<>();

    public static Warehouse getInstance(String testWarehouse) {
        return INSTANCES.computeIfAbsent(testWarehouse, k -> new Warehouse());
    }

    public void clearProducts() {
        products.clear();
        changedProducts.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null.");
        products.add(product);
        changedProducts.add(product);
    }

    public List<Product> getProducts() {
        // Return an unmodifiable copy to avoid test leakage
        return Collections.unmodifiableList(new ArrayList<>(products));
    }

    public void remove(Product product) {
        products.remove(product);
        changedProducts.remove(product);
    }

    public void remove (UUID id) {
        if (id == null) return;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p != null && id.equals(p.uuid())) {
                products.remove(i);
                changedProducts.remove(p);
                break;
            }
        }
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        Map<Category, List<Product>> grouped = new HashMap<>();
        for (Product p : products) {
            Category cat = p == null ? null : p.category();
            grouped.computeIfAbsent(cat, k -> new ArrayList<>()).add(p);
        }
        return Collections.unmodifiableMap(grouped);
    }

    public void updateProductPrice(UUID nonExistentId, BigDecimal newPrice) {
        if (nonExistentId == null) throw new IllegalArgumentException("id cannot be null");
        boolean updated = false;
        for (Product p : products) {
            if (p != null && nonExistentId.equals(p.uuid())) {
                p.price(newPrice); // antar att Product har price(BigDecimal) setter
                changedProducts.add(p);
                updated = true;
                break;
            }
        }
        if (!updated) {
            throw new NoSuchElementException("Product not found with id: " + nonExistentId);
        }
    }

    public Optional<Product> getProductById(UUID uuid) {
        if (uuid == null) return Optional.empty();
        for (Product p : products) {
            if (p != null && uuid.equals(p.uuid())) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public List<Perishable> expiredProducts() {
        List<Perishable> expired = new ArrayList<>();
        for (Product p : products) {
            if (p instanceof Perishable per && per.isExpired()) {
                expired.add(per);
            }
        }
        return Collections.unmodifiableList(expired);
    }

    public List<Shippable> shippableProducts() {
        List<Shippable> shippables = new ArrayList<>();
        for (Product p : products) {
            if (p instanceof Shippable s) {
                shippables.add(s);
            }
        }
        return Collections.unmodifiableList(shippables);
    }
}
