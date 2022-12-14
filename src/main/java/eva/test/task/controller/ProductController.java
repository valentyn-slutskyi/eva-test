package eva.test.task.controller;

import eva.test.task.dto.ProductRequestDto;
import eva.test.task.dto.ProductResponseDto;
import eva.test.task.mapper.ProductMapper;
import eva.test.task.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/product")
    public List<ProductResponseDto> findAllByNameFilter(@RequestParam String nameFilter) {
        return productService.getAllByNameFilter(nameFilter)
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/product")
    public ProductResponseDto add(@RequestBody @Valid ProductRequestDto requestDto) {
        return productMapper.mapToDto(
                productService.add(
                        productMapper.mapToModel(requestDto)));
    }
}
