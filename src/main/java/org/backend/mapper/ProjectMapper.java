package org.backend.mapper;

import org.backend.dto.response.ProjectResponse;
import org.backend.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectResponse toResponse(Project project) {
        if (project == null) return null;

        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .location(project.getLocation())
                .imageUrl(project.getImageUrl())
                .category(project.getCategory())
                .year(project.getYear())
                .materials(project.getMaterials())
                .description(project.getDescription())
                .createdAt(project.getCreatedAt())
                .build();
    }
}
