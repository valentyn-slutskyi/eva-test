package eva.test.task.service;

import eva.test.task.model.Product;
import java.util.List;

public interface ProductService {
    Product add(Product product);

    List<Product> getAllByNameFilter(String nameFilter);
}
