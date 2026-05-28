package devops_tracker.service;

import devops_tracker.entity.Pipeline;
import devops_tracker.repository.PipelineRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PipelineService {

    @Autowired
    private PipelineRepository pipelineRepository;

    // CREATE PIPELINE
    public Pipeline createPipeline(
            Pipeline pipeline) {

        return pipelineRepository.save(
                pipeline
        );
    }

    // GET ALL PIPELINES
    public List<Pipeline> getAllPipelines() {

        return pipelineRepository.findAll();
    }

    // GET PIPELINE BY ID
    public Pipeline getPipelineById(
            Long id) {

        return pipelineRepository
                .findById(id)

                .orElseThrow(() ->
                        new RuntimeException(
                                "Pipeline not found"
                        )
                );
    }

    // UPDATE PIPELINE
    public Pipeline updatePipeline(
            Long id,
            Pipeline updatedPipeline) {

        // FIND EXISTING
        Pipeline existingPipeline =

                pipelineRepository.findById(id)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Pipeline not found"
                                )
                        );

        // UPDATE FIELDS
        existingPipeline.setName(
                updatedPipeline.getName()
        );

        existingPipeline.setDescription(
                updatedPipeline.getDescription()
        );

        existingPipeline.setStatus(
                updatedPipeline.getStatus()
        );

        // SAVE UPDATED DATA
        return pipelineRepository.save(
                existingPipeline
        );
    }

    // DELETE PIPELINE
    public String deletePipeline(
            Long id) {

        Pipeline existingPipeline =

                pipelineRepository.findById(id)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Pipeline not found"
                                )
                        );

        pipelineRepository.delete(
                existingPipeline
        );

        return "Pipeline deleted successfully";
    }
}