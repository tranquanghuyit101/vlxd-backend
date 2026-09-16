package org.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    @Column(columnDefinition = "TEXT")
    private String content; // Chứa nội dung bài viết (HTML)
    private String imageUrl;
    private String category; // Tin tập đoàn, Tuyển dụng...
    private LocalDateTime createdAt = LocalDateTime.now();
}
