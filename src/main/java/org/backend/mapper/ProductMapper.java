package org.backend.mapper;

import org.backend.dto.response.CategoryResponse;
import org.backend.dto.response.ProductResponse;
import org.backend.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final CategoryMapper categoryMapper;

    public ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public ProductResponse toResponse(Product product) {
        if (product == null) return null;
        
        CategoryResponse categoryResponse = null;
        if (product.getCategory() != null) {
            categoryResponse = categoryMapper.toResponse(product.getCategory());
        }

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .unit(product.getUnit())
                .imageUrl(product.getImageUrl())
                .category(categoryResponse)
                .active(product.getActive())
                .createdAt(product.getCreatedAt())
                .build();
    }
}
