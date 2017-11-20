package com.example.eduardtsoy.springboottesttask.service;

import com.example.eduardtsoy.springboottesttask.domain.Product;
import com.example.eduardtsoy.springboottesttask.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(@Nonnull final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Nonnull
    public Product create(@Nonnull final Product product) {
        if (product.getId() != null) {
            throw new RuntimeException("\n  // Product ID should not be here");
        }
        if (product.getName() == null) {
            throw new RuntimeException("\n  // Product Name is required here");
        }
        return productRepository.create(product);
    }

    @Nonnull
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(final long id) {
        return productRepository.findById(id);
    }

    @Override
    @Nonnull
    public Product update(@Nonnull final Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("\n  // ID is required here");
        }
        return productRepository.update(product);
    }

    @Override
    @Nonnull
    public Product delete(final long id) {
        return productRepository.delete(id);
    }
}
