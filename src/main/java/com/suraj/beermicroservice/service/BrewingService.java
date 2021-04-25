package com.suraj.beermicroservice.service;

import com.suraj.beermicroservice.config.JmsConfig;
import com.suraj.beermicroservice.domain.Beer;
import com.suraj.beermicroservice.events.BrewBeerEvent;
import com.suraj.beermicroservice.mappers.BeerMapper;
import com.suraj.beermicroservice.repositories.BeerRepository;
import com.suraj.beermicroservice.service.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory(){
        List<Beer> beers =  beerRepository.findAll();

        beers.forEach( beer-> {
                    Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());
                    log.debug("Min QOH is: "+beer.getMinOnHand());
                    log.debug("Inventory is: "+invQOH);

                    if(beer.getMinOnHand() >= invQOH){
                        jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE,new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
                    }
                }
        );
    }
}
