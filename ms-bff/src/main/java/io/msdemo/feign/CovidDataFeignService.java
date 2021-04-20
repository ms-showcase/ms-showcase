package io.msdemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ms-covid", url = "${client.url.ms-covid:http://127.0.0.1:8889}",
        configuration = FeignClientsConfiguration.class)
public interface CovidDataFeignService {
    @RequestMapping(value = "data/{id}", method = RequestMethod.GET)
    String findById(@PathVariable("id") String id);

    @RequestMapping(value = "data/lastweek/{iso}/{date}", method = RequestMethod.GET)
    String findWeekOldRecords(@PathVariable("iso") final String iso,@PathVariable("date") final String date);

    @RequestMapping(value = "data/isocodes", method = RequestMethod.GET)
    String isoCodes();

    @RequestMapping(value = "data/statistics/{iso}", method = RequestMethod.GET)
    String lastYearStatistics(@PathVariable("iso") final String iso);
}
