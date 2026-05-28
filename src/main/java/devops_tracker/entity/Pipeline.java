package devops_tracker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pipelines")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pipeline {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )

    private Long id;

    // PIPELINE NAME
    @Column(nullable = false)
    private String name;

    // DESCRIPTION
    @Column(nullable = false)
    private String description;

    // STATUS
    @Column(nullable = false)
    private String status;

    // ONE PIPELINE -> MANY DEPLOYMENTS
    @OneToMany(
            mappedBy = "pipeline",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )

    @JsonManagedReference
    @Builder.Default

    private List<Deployment> deployments =
            new ArrayList<>();
}