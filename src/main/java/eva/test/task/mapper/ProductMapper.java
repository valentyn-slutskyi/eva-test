package eva.test.task.mapper;

import eva.test.task.dto.ProductRequestDto;
import eva.test.task.dto.ProductResponseDto;
import eva.test.task.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product mapToModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        return product;
    }
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setName(product.getName());
        responseDto.setDescription(product.getDescription());
        return responseDto;
    }
}
