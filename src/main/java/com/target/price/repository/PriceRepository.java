package com.target.price.repository;

import com.target.price.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends MongoRepository<Product, String> {
}
