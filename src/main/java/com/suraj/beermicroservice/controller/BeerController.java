package com.suraj.beermicroservice.controller;

import com.suraj.beermicroservice.mappers.BeerMapper;
import com.suraj.beermicroservice.model.BeerDto;
import com.suraj.beermicroservice.model.BeerStyleEnum;
import com.suraj.beermicroservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId")UUID beerId){
        return new ResponseEntity<>(beerMapper.BeerToBeerDto(beerRepository.findById(beerId).get()),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto){
        beerRepository.save(beerMapper.BeerDtoToBeer(beerDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId")UUID beerId, @RequestBody @Validated BeerDto beerDto){
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

            beerRepository.save(beer);
        });
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
      public ResponseEntity deleteBeer(@PathVariable("beerId") UUID beerId){
        beerRepository.findById(beerId).ifPresent(beer -> beerRepository.deleteById(beerId));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

