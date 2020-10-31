package com.info5059.casestudy.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/api/products")
    public ResponseEntity<Iterable<Product>> findAll() {
        Iterable<Product> products = productRepository.findAll();
        return new ResponseEntity<Iterable<Product>>(products, HttpStatus.OK);
    }
    @PutMapping("/api/products")
    public ResponseEntity<Product> updateOne(@RequestBody Product expense) {
        Product updatedProduct = productRepository.save(expense);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }
    @PostMapping("/api/products")
    public ResponseEntity<Product> addOne(@RequestBody Product expense) {
        Product newProduct = productRepository.save(expense);
        return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
    }
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Integer> deleteOne(@PathVariable String id) {
        return new ResponseEntity<Integer>(productRepository.deleteOne(id), HttpStatus.OK);
    }
}
