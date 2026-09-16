package org.backend.service;
import org.backend.dto.response.DashboardResponseDTO;

public interface DashboardService {
    DashboardResponseDTO getDashboardStats();
}