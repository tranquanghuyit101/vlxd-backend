package org.backend.service;

import org.backend.dto.request.ProductRequest;
import org.backend.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<ProductResponse> getAllProducts(Pageable pageable);
    List<ProductResponse> getAllProductsList();
    List<ProductResponse> getActiveProducts();
    ProductResponse getProductById(Long id);
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
    ProductResponse toggleActiveStatus(Long id, boolean activeStatus);
    Page<ProductResponse> searchProducts(String name, Long categoryId, Pageable pageable);
}
