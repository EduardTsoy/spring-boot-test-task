package com.example.eduardtsoy.springboottesttask.domain;

import javax.annotation.Nonnull;
import java.util.List;

public interface ProductRepository {

    @Nonnull
    Product create(@Nonnull final Product product);

    @Nonnull
    List<Product> findAll();

    Product findById(long id);

    @Nonnull
    Product update(@Nonnull final Product product);

    @Nonnull
    Product delete(long id);
}
