package devops_tracker.repository;

import devops_tracker.entity.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PipelineRepository extends JpaRepository<Pipeline, Long> {

}