package com.target.price.service;

import com.target.price.model.Product;
import com.target.price.repository.PriceRepository;
import com.target.price.utils.PriceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceService {
    private static final Logger log = LoggerFactory.getLogger(PriceService.class);

    private PriceRepository priceRepository;
    private PriceUtils priceUtils;

    public PriceService(PriceRepository priceRepository, PriceUtils priceUtils) {
        this.priceRepository = priceRepository;
        this.priceUtils = priceUtils;
    }

    public Product createPrice(Product product) throws Exception {
        if(!priceUtils.ValidatePrice(product)) {
            throw new Exception("Price details are not valid");
        }
        log.debug("Creating price for product id ${}", product.getProduct_id());
        return priceRepository.save(product);
    }

    public Optional<Product> findById(String id) {
        log.debug("Getting price for product id ${}",id);
        return priceRepository.findById(id);
    }

    public List<Product> findAll() {
        log.debug("Getting all prices");
        return priceRepository.findAll();
    }

    public boolean updateById(String id, Product product) throws Exception {
        if(!priceUtils.ValidatePrice(product)) {
            throw new Exception("Price details are not valid");
        }
        boolean updated = false;
        try {
            Product oldProduct = findById(product.getProduct_id()).get();
            if(product.getProduct_id().equals(id) && oldProduct.getProduct_id().equals(id)) {
                log.debug("Updating price for product id ${}", product.getProduct_id());
                priceRepository.save(product);
                updated = true;
            }
            return updated;
        } catch (Exception ex) {
            log.error("Updating price for product id ${} with exception ${}",id,ex);
            throw new RuntimeException("Price details are not valid", ex);
        }
    }

    public boolean deleteById(String id) {
        try {
            Product product = findById(id).get();
            log.debug("Deleting price for product id ${}", product.getProduct_id());
            priceRepository.delete(product);
            return true;
        } catch (Exception ex) {
            log.error("Deleting price for product id ${} with exception",id,ex);
            return false;
        }
    }

}
