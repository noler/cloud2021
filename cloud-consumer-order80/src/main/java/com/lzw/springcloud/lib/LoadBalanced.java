package com.lzw.springcloud.lib;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalanced {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
