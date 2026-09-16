package org.backend.repository;

import org.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrue();

    // Tìm kiếm sản phẩm theo tên (phân trang)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Lọc theo danh mục (phân trang)
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    // Tìm theo tên và danh mục (phân trang)
    Page<Product> findByNameContainingIgnoreCaseAndCategory_Id(String name, Long categoryId, Pageable pageable);
}
