package org.backend.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Tên vật liệu không được để trống")
    @Size(min = 3, max = 100, message = "Tên vật liệu phải từ 3 đến 100 ký tự")
    private String name;

    private String description;

    @NotNull(message = "Giá không được để trống")
    @Min(value = 0, message = "Giá sản phẩm không được nhỏ hơn 0")
    private BigDecimal price;

    @NotBlank(message = "Đơn vị tính không được để trống")
    private String unit;

    private String imageUrl;

    @NotNull(message = "Sản phẩm phải thuộc một danh mục")
    private Long categoryId;

    @Builder.Default
    private Boolean active = true;
}
