package com.suraj.beermicroservice.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suraj.beermicroservice.domain.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class BeerDtoTest extends BaseTest{

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeDto() throws JsonProcessingException {
        BeerDto beerDto = getDto();

        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);
    }

    @Test
    void testDeserialize() throws JsonProcessingException {
        String json = "{\"version\":null,\"createdDate\":\"2021-04-09T11:19:31+0530\",\"lastModifiedDate\":\"2021-04-09T11:19:31+0530\",\"beerName\":\"BeerName\",\"beerStyle\":\"ALE\",\"upc\":123123,\"price\":\"12.99\",\"quantityOnHand\":null,\"beerId\":\"d8ae3c03-5134-40a3-b233-b08f92c57e9d\"}";
        BeerDto beerDto = objectMapper.readValue(json,BeerDto.class);

        System.out.println(beerDto);
    }

}