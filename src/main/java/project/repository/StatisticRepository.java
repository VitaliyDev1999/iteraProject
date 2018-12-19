package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.Statistic;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {

}
