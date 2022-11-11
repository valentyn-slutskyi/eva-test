package eva.test.task.service;

import eva.test.task.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllByNameFilter(String nameFilter);
}
