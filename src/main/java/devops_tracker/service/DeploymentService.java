package devops_tracker.service;

import devops_tracker.entity.Deployment;
import devops_tracker.entity.Pipeline;
import devops_tracker.repository.DeploymentRepository;
import devops_tracker.repository.PipelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeploymentService {

    @Autowired
    private DeploymentRepository deploymentRepository;

    @Autowired
    private PipelineRepository pipelineRepository;

    // CREATE DEPLOYMENT
    public Deployment createDeployment(Long pipelineId, Deployment deployment) {

        Pipeline pipeline = pipelineRepository.findById(pipelineId)
                .orElseThrow(() -> new RuntimeException("Pipeline not found"));

        deployment.setPipeline(pipeline);

        return deploymentRepository.save(deployment);
    }

    // GET ALL DEPLOYMENTS
    public List<Deployment> getAllDeployments() {
        return deploymentRepository.findAll();
    }

    // UPDATE DEPLOYMENT STATUS
    // UPDATE DEPLOYMENT STATUS WITH VALIDATION
    public Deployment updateDeploymentStatus(Long deploymentId, String status) {

        Deployment deployment = deploymentRepository.findById(deploymentId)
                .orElseThrow(() -> new RuntimeException("Deployment not found"));

        String currentStatus = deployment.getStatus();

        // VALID TRANSITIONS
        boolean isValid = false;

        if (currentStatus.equals("PENDING") && status.equals("RUNNING")) {
            isValid = true;
        }

        else if (currentStatus.equals("RUNNING") &&
                (status.equals("SUCCESS") || status.equals("FAILED"))) {
            isValid = true;
        }

        else if (currentStatus.equals("FAILED") &&
                status.equals("ROLLED_BACK")) {
            isValid = true;
        }

        if (!isValid) {
            throw new RuntimeException(
                    "Invalid status transition from "
                            + currentStatus + " to " + status);
        }

        deployment.setStatus(status);

        return deploymentRepository.save(deployment);
    }
}