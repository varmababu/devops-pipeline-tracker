package devops_tracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deployments")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Deployment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String version;

    private String environment;

    private String status;

    // MANY deployments belong to ONE pipeline
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "pipeline_id", nullable = false)

    @JsonBackReference
    private Pipeline pipeline;
}