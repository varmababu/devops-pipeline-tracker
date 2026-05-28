package devops_tracker.controller;

import devops_tracker.entity.Pipeline;
import devops_tracker.service.PipelineService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pipelines")

@CrossOrigin(origins = "http://localhost:5173")

public class PipelineController {

    @Autowired
    private PipelineService pipelineService;

    // CREATE PIPELINE
    @PostMapping
    public Pipeline createPipeline(
            @RequestBody Pipeline pipeline) {

        System.out.println(
                "CREATING PIPELINE: "
                        + pipeline.getName()
        );

        return pipelineService
                .createPipeline(pipeline);
    }

    // GET ALL PIPELINES
    @GetMapping
    public List<Pipeline> getAllPipelines() {

        return pipelineService
                .getAllPipelines();
    }

    // GET PIPELINE BY ID
    @GetMapping("/{id}")
    public Pipeline getPipelineById(
            @PathVariable Long id) {

        return pipelineService
                .getPipelineById(id);
    }

    // UPDATE PIPELINE
    @PutMapping("/{id}")
    public Pipeline updatePipeline(

            @PathVariable Long id,

            @RequestBody
            Pipeline pipeline) {

        return pipelineService
                .updatePipeline(
                        id,
                        pipeline
                );
    }

    // DELETE PIPELINE
    @DeleteMapping("/{id}")
    public String deletePipeline(
            @PathVariable Long id) {

        return pipelineService
                .deletePipeline(id);
    }
}