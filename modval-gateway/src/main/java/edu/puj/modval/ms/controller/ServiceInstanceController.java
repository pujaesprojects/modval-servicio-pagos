package edu.puj.modval.ms.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class ServiceInstanceController {
    private final DiscoveryClient discoveryClient;
    private static final String CONVENIO = "convenio";

    public ServiceInstanceController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @GetMapping(value = "/convenios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, List<String>>> getServices() {
        List<String> convenios = this.discoveryClient.getServices()
                .stream()
                .map(serviceId -> {
                    List<ServiceInstance> serviceInstance = this.discoveryClient.getInstances(serviceId);
                    if (serviceInstance.size() > 0 && serviceInstance.get(0).getMetadata().containsKey(CONVENIO)) {
                        return serviceInstance.get(0).getMetadata().get(CONVENIO);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Map<String, List<String>> response = new HashMap<>();
        response.put("convenios", convenios);
        return ResponseEntity.ok(response);
    }
}
