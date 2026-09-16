package org.backend.dto.response;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardResponseDTO {
    private long totalProducts;
    private long totalCategories;
    private long totalContacts;
    private List<ContactChartData> contactStats; // Gọi class riêng ở trên
}