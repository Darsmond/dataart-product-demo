package service;

import com.dataart.productstest.dao.ProductDao;
import com.dataart.productstest.model.Product;
import com.dataart.productstest.model.ProductGroupCount;
import com.dataart.productstest.service.ProductService;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @SpyBean
    private ProductService productService;

    @MockBean
    private ProductDao productDao;

    @BeforeEach
    void setUp() {
        Mockito.reset(productDao);
    }

    @Test
    public void getProductsGroupTest_fail() {
        Mockito.when(productDao.getProductGroups()).thenThrow(RuntimeException.class);
        Assertions.assertThrows(RuntimeException.class, () -> productService.getProductGroups(), "");
    }

    @Test
    public void getProductsGroupTest_success() {
        Mockito.when(productDao.getProductGroups()).thenReturn(List.of(new ProductGroupCount()));
        List<ProductGroupCount> productGroupCounts = productService.getProductGroups();
        Assertions.assertEquals(1L, productGroupCounts.size());
    }

    @Test
    public void getProductsByGroupIdTest_fail() {
        Mockito.when(productDao.getProducts(1L)).thenThrow(RuntimeException.class);
        Assertions.assertThrows(RuntimeException.class, () -> productService.getProductsByGroupId(1L));
    }

    @Test
    public void getProductsByGroupIdTest_success() {
        Mockito.when(productDao.getProducts(Mockito.any())).thenReturn(List.of(new Product()));
        List<Product> result = productService.getProductsByGroupId(1L);
        Assertions.assertEquals(1L, result.size());
    }
}
