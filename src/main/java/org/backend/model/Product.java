package org.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên vật liệu không được để trống")
    @Size(min = 3, max = 100, message = "Tên vật liệu phải từ 3 đến 100 ký tự")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Giá không được để trống")
    @Min(value = 0, message = "Giá sản phẩm không được nhỏ hơn 0")
    private BigDecimal price;

    @NotBlank(message = "Đơn vị tính không được để trống")
    private String unit;
    private String imageUrl;

    @NotNull(message = "Sản phẩm phải thuộc một danh mục")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
