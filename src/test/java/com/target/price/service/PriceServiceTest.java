package com.target.price.service;

import com.target.price.model.Price;
import com.target.price.model.Product;
import com.target.price.repository.PriceRepository;
import com.target.price.utils.PriceUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static  org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {
    private Product product;
    private PriceService priceService;
    private PriceUtils priceUtils;

    @Mock
    private PriceRepository priceRepository;

    @Before
    public void setup() {
        product = new Product();
        priceUtils = new PriceUtils();
        priceService = new PriceService(priceRepository, priceUtils);
    }

    @Test
    public void createPriceSuccess() throws Exception {
        this.resetData();
        doReturn(product).when(priceRepository).save(Mockito.any(Product.class));

        Product pr = priceService.createPrice(product);
        assertEquals(product, pr);
        verify(priceRepository, times(1)).save(Mockito.any(Product.class));
    }

    @Test
    public void findAllPrices() {
        this.resetData();
        List<Product> p = new ArrayList<>();
        p.add(product);
        doReturn(p).when(priceRepository).findAll();

        List<Product> pr = priceService.findAll();

        assertEquals(pr.size(),1);
        verify(priceRepository, times(1)).findAll();
    }

    @Test
    public void findById() {
        this.resetData();
        Optional<Product> returnProduct = Optional.of(product);
        doReturn(returnProduct).when(priceRepository).findById(Mockito.anyString());

        Optional<Product> pr = priceService.findById(product.getProduct_id());

        assertEquals(product, pr.get());
        verify(priceRepository, times(1)).findById(Mockito.anyString());
    }

    @Test
    public void updatePriceByIdSuccess() throws Exception {
        this.resetData();

        Optional<Product> returnProduct = Optional.of(product);
        doReturn(product).when(priceRepository).save(Mockito.any(Product.class));
        doReturn(returnProduct).when(priceRepository).findById(Mockito.anyString());

        Boolean result = priceService.updateById(product.getProduct_id(), product);

        assertEquals(true, result);
        verify(priceRepository, times(1)).findById(Mockito.anyString());
        verify(priceRepository, times(1)).save(Mockito.any(Product.class));
    }

    @Test
    public void updatePriceByIdNotFound() throws Exception {
        this.resetData();

        doThrow(new NoSuchElementException()).when(priceRepository).findById(Mockito.anyString());

        boolean result = priceService.updateById(product.getProduct_id(), product);

        assertFalse(result);
        verify(priceRepository, times(1)).findById(Mockito.anyString());
        verify(priceRepository, times(0)).save(Mockito.any(Product.class));
    }

    @Test(expected = RuntimeException.class)
    public void updatePriceByIdException() throws Exception {
        this.resetData();

        Optional<Product> returnProduct = Optional.of(product);
        doReturn(returnProduct).when(priceRepository).findById(Mockito.anyString());
        doThrow(new RuntimeException()).when(priceRepository).save(product);

        priceService.updateById(product.getProduct_id(), product);

        verify(priceRepository, times(1)).findById(Mockito.anyString());
        verify(priceRepository, times(1)).save(Mockito.any(Product.class));
    }

    private void resetData() {
        product.setProduct_id("123456");
        Price price = new Price("4-5",4,5);
        product.setPrice(price);
    }
}
