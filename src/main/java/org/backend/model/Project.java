package org.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên công trình không được để trống")
    private String name;

    @NotBlank(message = "Địa điểm không được để trống")
    private String location;

    private String imageUrl;

    private String category; // e.g., Cao ốc văn phòng, Đô thị sinh thái, Hạ tầng giao thông

    private String year; // e.g., 2023 - 2024

    private String materials; // e.g., Xi măng Xuân Thành, Thép Hòa Phát

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
