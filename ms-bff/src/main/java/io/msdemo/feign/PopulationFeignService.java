package io.msdemo.feign;

import java.util.concurrent.CompletableFuture;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.msdemo.dto.PopulationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ms-population", url = "${client.url.ms-population:http://127.0.0.1:8091}",
    configuration = FeignClientsConfiguration.class)
public interface PopulationFeignService {

    @RequestMapping(value = "population/{iso}", method = RequestMethod.GET)
    @CircuitBreaker(name = "ms-population", fallbackMethod = "populationFallback")
    @Bulkhead(name = "ms-population", type = Bulkhead.Type.THREADPOOL)
    @TimeLimiter(name = "ms-population")
    CompletableFuture<PopulationDto> population(@PathVariable("iso") String iso);

    default CompletableFuture<PopulationDto> populationFallback(Throwable throwable){
        return CompletableFuture.completedFuture(PopulationDto.builder().country("N/A").countryCode("N/A").population("N/A").build());
    }

}
