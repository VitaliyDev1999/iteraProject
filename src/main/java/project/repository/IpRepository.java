package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.IdIpEntity;

public interface IpRepository extends JpaRepository<IdIpEntity, Long> {
}
