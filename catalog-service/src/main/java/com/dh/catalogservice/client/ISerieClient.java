package com.dh.catalogservice.client;

import com.dh.catalogservice.feing.CustomLoadBalancerConfiguration;
import com.dh.catalogservice.model.Serie;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "serie-service")
@LoadBalancerClient(name = "serie-service", configuration = CustomLoadBalancerConfiguration.class)

public interface ISerieClient {

    @GetMapping("/api/v1/series/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre);

    @PostMapping("/api/v1/series/save")
    String createSerie(@RequestBody Serie seriee);


}
