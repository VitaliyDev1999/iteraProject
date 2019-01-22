package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.entity.Statistic;
import project.entity.StatisticRequest;

public interface StatisticRequestRepository extends JpaRepository<StatisticRequest, Long> {
    @Query(value = "SELECT * FROM statistic_range s where s.ip_address_id = :id and s.range = :range",
            nativeQuery = true)
    StatisticRequest findByIdEquals(@Param("id") Long id, @Param("range") String range);

    @Query(value = "SELECT * FROM (SELECT * FROM statistic_range s where s.ip_address_id = :id ORDER BY id desc) as statistic_range ORDER BY id desc LIMIT 1",
            nativeQuery = true)
    StatisticRequest findAllByIdMax(@Param("id") Long id);
}
