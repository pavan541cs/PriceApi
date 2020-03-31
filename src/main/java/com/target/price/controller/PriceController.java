package com.target.price.controller;

import com.target.price.model.Product;
import com.target.price.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    ResponseEntity<List<Product>> getAllPrices() {
        List<Product> products = priceService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Product> createPrice(@Valid @RequestBody Product product) {
        try {
            Product newProuct = priceService.createPrice(product);
            return new ResponseEntity<>(newProuct, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> findOnePrice(@PathVariable String id) {
        Optional<Product> product = priceService.findById(id);
        HttpStatus myStatus = HttpStatus.OK;
        if(!product.isPresent()) {
            myStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, myStatus);
        }
        return new ResponseEntity<>(product.get(), myStatus);
    }

    @PutMapping("/{id}")
    ResponseEntity updateOnePrice(@PathVariable String id, @Valid @RequestBody Product product) {
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            if(!priceService.updateById(id, product)) {
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception ex) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(status);
    }
}
