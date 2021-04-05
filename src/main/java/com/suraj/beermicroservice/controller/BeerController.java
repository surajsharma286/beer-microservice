package com.suraj.beermicroservice.controller;

import com.suraj.beermicroservice.model.BeerDto;
import com.suraj.beermicroservice.model.BeerStyleEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerId(@PathVariable("beerId")UUID beerId){
        return new ResponseEntity<>(BeerDto.builder().id(UUID.randomUUID()).beerName("Galaxy").beerStyle(BeerStyleEnum.ALE).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto){
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId")UUID beerId, @RequestBody @Validated BeerDto beerDto){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
      public ResponseEntity deleteBeer(@PathVariable("beerId") UUID beerId){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

