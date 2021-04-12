package io.msdemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="MS-COVID")
public interface CovidDataFeignService {
    @RequestMapping(value = "data/{id}", method = RequestMethod.GET)
    String getCovidData(@PathVariable("id") String id);
}
