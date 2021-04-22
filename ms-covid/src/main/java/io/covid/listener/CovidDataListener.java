package io.covid.listener;

import io.covid.db.model.CovidData;
import io.covid.db.repository.CovidDataRepository;
import io.covid.dto.CovidDto;
import io.covid.mapper.CovidDataMapper;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
public class CovidDataListener {

    @Autowired
    private CovidDataRepository covidDataRepository;

    @RabbitListener(queues = "${rabbitmq.covid.queue}", containerFactory = "rabbitListenerContainerFactoryJson")
    public void receivedCovidData(final CovidDto covidDto){
        CovidData data = CovidDataMapper.INSTANCE.covidDataDtoToCovidDataModel(covidDto);
        covidDataRepository.save(data);
        log.info("Message from COVID19 queue consumed! " + data.getIsoCodeAndDate());
    }
}
