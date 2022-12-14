package eva.test.task.service;

import eva.test.task.model.Product;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import eva.test.task.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllByNameFilter(String nameFilter) {
        Pattern pattern = Pattern.compile(nameFilter);
        return productRepository
                .findAll()
                .parallelStream()
                .filter(product -> !pattern.matcher(product.getName()).matches())
                .collect(Collectors.toList());
    }
}
