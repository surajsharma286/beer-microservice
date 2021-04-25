package com.suraj.beermicroservice.events;

import com.suraj.beermicroservice.model.BeerDto;

public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
