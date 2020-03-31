package com.target.price.utils;

import com.target.price.model.Product;
import org.springframework.stereotype.Component;

@Component
public class PriceUtils {

    public boolean ValidatePrice(Product product) {
        if(!isvalidPriceValue(product.getPrice().getMin())) {
            return false;
        } else if(!isvalidPriceValue(product.getPrice().getMax())) {
            return false;
        } else if(!isInPriceRange(product.getPrice().getRange(), product.getPrice().getMin())) {
            return false;
        } else if(!isInPriceRange(product.getPrice().getRange(), product.getPrice().getMax())) {
            return false;
        }
        return true;
    }

    public boolean isvalidPriceValue(double price) {
        if (price > 0) {
            return true;
        }
        return false;
    }

    public boolean isInPriceRange(String range, double price) {
        String[] arr = range.split("-");
        double min = Double.parseDouble(arr[0]);
        double max = Double.parseDouble(arr[1]);
        if (price >= min && price <= max) {
            return true;
        }
        return false;
    }

}
