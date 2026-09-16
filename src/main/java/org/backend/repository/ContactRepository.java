package org.backend.repository;

import org.backend.model.ContactRequest;
import org.backend.dto.response.ContactChartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface ContactRepository extends JpaRepository<ContactRequest, Long> {

    @Query(value = "SELECT DATE_FORMAT(created_at, '%d/%m') as label, COUNT(id) as value " +
                   "FROM contact_request " +
                   "GROUP BY DATE_FORMAT(created_at, '%d/%m') " +
                   "ORDER BY MIN(created_at) ASC", nativeQuery = true)
    List<Object[]> getRawContactStatsForLast7Days();

    default List<ContactChartData> getContactStatsForLast7Days() {
        return getRawContactStatsForLast7Days().stream()
                .map(row -> new ContactChartData(
                        (String) row[0], 
                        ((Number) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }
}