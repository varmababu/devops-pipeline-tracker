package devops_tracker.repository;

import devops_tracker.entity.Deployment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeploymentRepository extends JpaRepository<Deployment, Long> {
}