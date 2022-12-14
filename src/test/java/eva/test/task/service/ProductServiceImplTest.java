package eva.test.task.service;

import eva.test.task.model.Product;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import eva.test.task.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void returnValidProductsByNameFilter_ok() {
        String nameFilter = "^.*[eva].*$";
        Product Pampers = new Product(1L, "Pampers", "Baby's product");
        Product Socks = new Product(2L, "Socks", "Man's product");
        Mockito.when(productRepository.findAll()).thenReturn(List.of(Pampers, Socks));
        List<Product> actual = productService.getAllByNameFilter(nameFilter);
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(Socks, actual.get(0));
    }
}
