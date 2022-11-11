package eva.test.task.service;

import eva.test.task.model.Product;
import eva.test.task.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void returnValidProductsByNameFilter_ok() {
        String nameFilter = "^.*[eva].*$";
        Product firstProduct = new Product(1L, "Pampers", "Baby's product");
        Product secondProduct = new Product(2L, "Socks", "Man's product");
        Mockito.when(productRepository.findAll()).thenReturn(List.of(firstProduct, secondProduct));
        List<Product> actual = productService.getAllByNameFilter(nameFilter);
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(secondProduct, actual.get(0));
    }
}