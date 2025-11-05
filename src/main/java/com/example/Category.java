package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Category {
    // ... existing code ...
    private static final Map<String, Category> CACHE = new ConcurrentHashMap<>();
    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }
        // Normalize: first letter uppercase, rest lowercase (preserve the rest as-is if you prefer)
        String normalized = Character.toUpperCase(trimmed.charAt(0)) + trimmed.substring(1);
        // Return cached instance (flyweight)
        return CACHE.computeIfAbsent(normalized, key -> new Category(key));
    }

    public String getName() {
        return name;
    }
    // ... existing code ...
}
