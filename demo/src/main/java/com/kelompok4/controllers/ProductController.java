package com.kelompok4.controllers;

import java.util.List;
import java.util.Optional;
    
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kelompok4.models.entities.Product;
import com.kelompok4.services.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product create(@RequestBody Product product){
        return productService.save(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")  
    public Optional<Product> findOne(@PathVariable("id")Long id){
        return productService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id")Long id){
        productService.removeOne(id);
    }

    @GetMapping("/name/{name}")
    public List<Product> findByName(@PathVariable("name")String name){
        return productService.findByName(name);
    }

}
