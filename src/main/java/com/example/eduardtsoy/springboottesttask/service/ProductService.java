package com.example.eduardtsoy.springboottesttask.service;

import com.example.eduardtsoy.springboottesttask.domain.Product;

import javax.annotation.Nonnull;
import java.util.List;

public interface ProductService {

    @Nonnull
    Product create(@Nonnull final Product product);

    @Nonnull
    List<Product> findAll();

    Product findById(final long id);

    @Nonnull
    Product update(@Nonnull final Product product);

    @Nonnull
    Product delete(final long id);
}
