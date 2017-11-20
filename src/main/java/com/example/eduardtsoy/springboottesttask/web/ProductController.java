package com.example.eduardtsoy.springboottesttask.web;

import com.example.eduardtsoy.springboottesttask.domain.Product;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Nonnull;
import java.util.List;

public interface ProductController {

    @Nonnull
    Product create(@RequestBody final Product product);

    List<Product> findAll();

    Product findById(@RequestBody final long id);

    @Nonnull
    Product update(@RequestBody final Product product);

    @Nonnull
    Product delete(@RequestBody final Long id);
}
