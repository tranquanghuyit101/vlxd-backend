package org.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class ContactRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String message;
    private String status = "Chưa liên hệ";
    private LocalDateTime createdAt = LocalDateTime.now();
}