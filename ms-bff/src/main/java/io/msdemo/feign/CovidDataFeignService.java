package io.msdemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ms-covid", url = "127.0.0.1:8080")
public interface CovidDataFeignService {
    @RequestMapping(value = "data/{id}", method = RequestMethod.GET)
    String findById(@PathVariable("id") String id);

    @RequestMapping(value = "data/lastweek/{iso}/{date}", method = RequestMethod.GET)
    String findWeekOldRecords(@PathVariable("iso") final String iso,@PathVariable("date") final String date);

    @RequestMapping(value = "data/isocodes", method = RequestMethod.GET)
    String isoCodes();
}
