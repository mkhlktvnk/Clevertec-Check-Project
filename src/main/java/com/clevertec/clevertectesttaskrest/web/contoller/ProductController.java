package com.clevertec.clevertectesttaskrest.web.contoller;

import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.service.ProductService;
import com.clevertec.clevertectesttaskrest.web.model.ProductModel;
import com.clevertec.clevertectesttaskrest.web.model.converter.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @GetMapping("/products")
    public List<ProductModel> getProducts(@PageableDefault Pageable pageable) {
        return productMapper.allToModel(productService.getByPaging(pageable));
    }

    @GetMapping("/products/{id}")
    public ProductModel getProduct(@PathVariable Long id) {
        return productMapper.toModel(productService.getById(id));
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductModel createProduct(@RequestBody ProductModel productModel) {
        Product product = productMapper.toEntity(productModel);
        return productMapper.toModel(productService.create(product));
    }

    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id, @RequestBody ProductModel productModel) {
        productService.update(id, productMapper.toEntity(productModel));
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
