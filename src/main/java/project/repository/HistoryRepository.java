package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.entity.HistoryDbEntity;
import project.entity.IdIpEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryDbEntity, Long> {
    @Query(value = "SELECT s FROM history s where s.ip_address_id = :idIpEntity ORDER BY s.id desc LIMIT 5",
            nativeQuery = true)
    List<HistoryDbEntity> findAllByIpEqualsOrderById(@Param("idIpEntity") IdIpEntity idIpEntity);

}
