package com.suraj.beermicroservice.service.inventory;

import com.suraj.beermicroservice.bootstrap.BeerLoader;
import com.suraj.beermicroservice.domain.Beer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setup(){

    }

    @Test
    void getOnHandInventory(){
        Integer qoh = beerInventoryService.getOnhandInventory(UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb"));
    }

}