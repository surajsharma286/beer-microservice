package com.suraj.beermicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suraj.beermicroservice.model.BeerDto;
import com.suraj.beermicroservice.model.BeerStyleEnum;
import com.suraj.beermicroservice.repositories.BeerRepository;
import com.suraj.beermicroservice.service.BeerService;
import com.suraj.beermicroservice.service.inventory.BeerInventoryServiceRestTemplateImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "com.suraj.beermicroservice")
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerRepository beerRepository;

    @MockBean
    BeerService beerService;

    @MockBean
    BeerInventoryServiceRestTemplateImpl beerInventoryServiceRestTemplate;

    @MockBean
    JmsTemplate jmsTemplate;

    @Test
    void getBeerId() throws Exception {
        given(beerService.getById(any(),anyBoolean())).willReturn(getValidBeerDto());

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveNewBeer() throws Exception{
        BeerDto beerDto =  getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception{
        BeerDto beerDto =  getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    private BeerDto getValidBeerDto(){
        return BeerDto.builder()
                .beerName("Pale Ale")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal("11.95"))
                .upc("112123")
                .build();
    }

}