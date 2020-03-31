package com.target.price.utils;

import com.target.price.model.Price;
import com.target.price.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class PriceUtilsTest {
    private Product product;

    @InjectMocks
    private PriceUtils priceUtils;

    @Before
    public void setup() {
        product = new Product();
    }

    @Test
    public void PriceInRange() {
        product.setProduct_id("123456");
        Price price = new Price("4-5",4,5);
        product.setPrice(price);

        assertTrue(priceUtils.isInPriceRange(price.getRange(), price.getMin()));
    }

    @Test
    public void PriceNotInRange() {
        product.setProduct_id("123456");
        Price price = new Price("4-5",4,7);
        product.setPrice(price);

        assertTrue(priceUtils.isInPriceRange(price.getRange(), price.getMin()));
        assertFalse(priceUtils.isInPriceRange(price.getRange(), price.getMax()));
    }

    @Test
    public void IsPriceValid() {
        product.setProduct_id("123456");
        Price price = new Price("4-5",-4,7);
        product.setPrice(price);

        assertTrue(priceUtils.isvalidPriceValue(price.getMax()));
        assertFalse(priceUtils.isvalidPriceValue(price.getMin()));
    }

}
