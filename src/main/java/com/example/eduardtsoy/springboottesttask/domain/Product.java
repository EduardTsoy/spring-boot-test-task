package com.example.eduardtsoy.springboottesttask.domain;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private Long quantity;

    public Product() {
    }

    public Product(final Long id,
                   final String name,
                   final String brand,
                   final BigDecimal price,
                   final Long quantity) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    /* ---------------------------------
     * Standard POJO getters and setters
     */

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }
}
