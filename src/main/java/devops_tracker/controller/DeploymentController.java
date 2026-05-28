package devops_tracker.controller;

import devops_tracker.entity.Deployment;
import devops_tracker.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deployments")

public class DeploymentController {

    @Autowired
    private DeploymentService deploymentService;

    // CREATE DEPLOYMENT
    @PostMapping("/pipeline/{pipelineId}")
    public Deployment createDeployment(
            @PathVariable Long pipelineId,
            @RequestBody Deployment deployment) {

        return deploymentService.createDeployment(pipelineId, deployment);
    }

    // GET ALL DEPLOYMENTS
    @GetMapping
    public List<Deployment> getAllDeployments() {
        return deploymentService.getAllDeployments();
    }

    // UPDATE DEPLOYMENT STATUS
    @PutMapping("/{deploymentId}/status")
    public Deployment updateDeploymentStatus(
            @PathVariable Long deploymentId,
            @RequestParam String status) {

        return deploymentService.updateDeploymentStatus(deploymentId, status);
    }
}