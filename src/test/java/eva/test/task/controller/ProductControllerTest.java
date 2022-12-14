package eva.test.task.controller;

import eva.test.task.model.Product;
import eva.test.task.service.ProductService;
import java.util.List;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    public static final String ENDPOINT = "shop/product?nameFilter=";
    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void getProducts_ok() {
        String nameFilter = "^.*[eva].*$";
        List<Product> mockProducts = List.of(
                new Product(1L, "Pampers", "Baby's product"),
                new Product(2L, "Socks", "Man's product"),
                new Product(3L, "Soap", "Family's product"),
                new Product(4L, "Toothbrush", "Woman's product"));
        Mockito.when(productService.getAllByNameFilter(nameFilter))
                .thenReturn(List.of(mockProducts.get(1), mockProducts.get(3)));
        RestAssuredMockMvc.when()
                .get(ENDPOINT + nameFilter)
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(2))
                .body("[0].id", Matchers.equalTo(2))
                .body("[0].name", Matchers.equalTo("Socks"))
                .body("[0].description", Matchers.equalTo("Man's product"))
                .body("[1].id", Matchers.equalTo(4))
                .body("[1].name", Matchers.equalTo("Toothbrush"))
                .body("[1].description", Matchers.equalTo("Woman's product"));
    }
}
