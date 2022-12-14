package eva.test.task.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotBlank(message = "Name may not be blank")
    @Size(max = 100, message = "Length of name may not be greater than 100 symbols")
    private String name;
    @NotBlank(message = "Description may not be blank")
    @Size(max = 1000, message = "Length of description may not be greater than 1000 symbols")
    private String description;
}
