package com.example.eduardtsoy.springboottesttask.web;

import com.example.eduardtsoy.springboottesttask.domain.Product;
import com.example.eduardtsoy.springboottesttask.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import java.util.List;

@RestController
@RequestMapping(value = "/product", consumes = {"application/json"})
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Autowired
    public ProductControllerImpl(@Nonnull final ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Nonnull
    public Product create(@RequestBody final Product product) {
        return productService.create(product);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> findAll() {
        return productService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable("id") final long id) {
        return productService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Nonnull
    public Product update(@RequestBody final Product product) {
        return productService.update(product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Nonnull
    public Product delete(@PathVariable("id") final Long id) {
        return productService.delete(id);
    }
}
