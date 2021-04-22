package io.msdemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ms-population", url = "${client.url.ms-population:http://127.0.0.1:8091}",
    configuration = FeignClientsConfiguration.class)
public interface PopulationFeignService {

    @RequestMapping(value = "population/{iso}", method = RequestMethod.GET)
    String population(@PathVariable("iso") String iso);
}
