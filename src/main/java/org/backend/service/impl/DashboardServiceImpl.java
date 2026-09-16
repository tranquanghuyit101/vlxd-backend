package org.backend.service.impl;

import org.backend.dto.response.DashboardResponseDTO;
import org.backend.repository.CategoryRepository;
import org.backend.repository.ContactRepository;
import org.backend.repository.ProductRepository;
import org.backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private ContactRepository contactRepository;

    @Override
    public DashboardResponseDTO getDashboardStats() {
        return DashboardResponseDTO.builder()
                .totalProducts(productRepository.count())
                .totalCategories(categoryRepository.count())
                .totalContacts(contactRepository.count())
                .contactStats(contactRepository.getContactStatsForLast7Days()) // Dữ liệu thật
                .build();
    }
}