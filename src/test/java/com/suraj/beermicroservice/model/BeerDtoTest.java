package com.suraj.beermicroservice.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.sfg.brewery.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

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
        String json = "{\"id\":\"b7087687-f523-4402-a763-337c7ebe7ee9\",\"version\":null,\"createdDate\":\"2021-04-14T17:32:05+0530\",\"lastModifiedDate\":\"2021-04-14T17:32:05+0530\",\"beerName\":\"BeerName\",\"beerStyle\":\"PALE_ALE\",\"upc\":\"123123\",\"price\":\"12.99\",\"quantityOnHand\":null}";
        BeerDto beerDto = objectMapper.readValue(json,BeerDto.class);

        System.out.println(beerDto);
    }

}