package org.backend.controller;

import org.backend.dto.response.DashboardResponseDTO;
import org.backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/stats")
    public DashboardResponseDTO getStats() {
        return dashboardService.getDashboardStats();
    }
}