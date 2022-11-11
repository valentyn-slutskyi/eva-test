package eva.test.task.service;

import eva.test.task.model.Product;
import eva.test.task.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllByNameFilter(String nameFilter) {
        return productRepository
                .findAll()
                .parallelStream()
                .filter(product -> !product.getName().matches(nameFilter))
                .collect(Collectors.toList());
    }
}
