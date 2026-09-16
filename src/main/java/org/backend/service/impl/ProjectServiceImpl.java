package org.backend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.backend.dto.request.ProjectRequest;
import org.backend.dto.response.ProjectResponse;
import org.backend.mapper.ProjectMapper;
import org.backend.model.Project;
import org.backend.repository.ProjectRepository;
import org.backend.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy công trình với id: " + id));
        return projectMapper.toResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectRequest request) {
        Project project = Project.builder()
                .name(request.getName())
                .location(request.getLocation())
                .imageUrl(request.getImageUrl())
                .category(request.getCategory())
                .year(request.getYear())
                .materials(request.getMaterials())
                .description(request.getDescription())
                .build();

        Project savedProject = projectRepository.save(project);
        return projectMapper.toResponse(savedProject);
    }

    @Override
    @Transactional
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy công trình với id: " + id));

        project.setName(request.getName());
        project.setLocation(request.getLocation());
        project.setImageUrl(request.getImageUrl());
        project.setCategory(request.getCategory());
        project.setYear(request.getYear());
        project.setMaterials(request.getMaterials());
        project.setDescription(request.getDescription());

        Project updatedProject = projectRepository.save(project);
        return projectMapper.toResponse(updatedProject);
    }

    @Override
    @Transactional
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new EntityNotFoundException("Không tìm thấy công trình với id: " + id);
        }
        projectRepository.deleteById(id);
    }
}
