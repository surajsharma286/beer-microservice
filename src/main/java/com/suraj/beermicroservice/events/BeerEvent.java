package com.suraj.beermicroservice.events;

import com.suraj.beermicroservice.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = -8727095884089348972L;

    private final BeerDto beerDto;
}
