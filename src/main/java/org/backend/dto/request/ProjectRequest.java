package org.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectRequest {

    @NotBlank(message = "Tên công trình không được để trống")
    private String name;

    @NotBlank(message = "Địa điểm không được để trống")
    private String location;

    private String imageUrl;

    private String category;

    private String year;

    private String materials;

    private String description;
}
