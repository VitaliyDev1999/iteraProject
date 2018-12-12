package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.HistoryDbEntity;

public interface HistoryRepository extends JpaRepository<HistoryDbEntity, Long> {
}
