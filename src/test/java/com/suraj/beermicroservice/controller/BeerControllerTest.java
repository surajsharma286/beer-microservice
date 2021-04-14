package com.suraj.beermicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.suraj.beermicroservice.domain.Beer;
import com.suraj.beermicroservice.model.BeerDto;
import com.suraj.beermicroservice.model.BeerStyleEnum;
import com.suraj.beermicroservice.repositories.BeerRepository;
import com.suraj.beermicroservice.service.BeerService;
import com.suraj.beermicroservice.service.inventory.BeerInventoryService;
import com.suraj.beermicroservice.service.inventory.BeerInventoryServiceRestTemplateImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static  org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
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

    @Test
    void getBeerId() throws Exception {
        given(beerService.getById(any(),anyBoolean())).willReturn(getValidBeerDto());

        mockMvc.perform(get("/api/v1/beer/{beerId}" , UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer-get",
                        pathParameters(
                        parameterWithName("beerId").description("UUID of Desired beer to get")
                        ),
                        responseFields(
                             fieldWithPath("id").description("Id"),
                             fieldWithPath("version").description("Version Number"),
                             fieldWithPath("createdDate").description("Date Created"),
                             fieldWithPath("lastModifiedDate").description("Date Updated"),
                             fieldWithPath("beerName").description("Beer Name"),
                             fieldWithPath("beerStyle").description("Beer Style"),
                             fieldWithPath("upc").description("UPC of Beer"),
                             fieldWithPath("price").description("Price"),
                             fieldWithPath("quantityOnHand").description("Quantity On Hand")
                        )

                ));
    }

    @Test
    void saveNewBeer() throws Exception{
        BeerDto beerDto =  getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer-post",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("beerName").description("Name of the Beer"),
                                fields.withPath("beerStyle").description("Style of Beer"),
                                fields.withPath("upc").description("Beer UPC").attributes(),
                                fields.withPath("price").description("Beer Price"),
                                fields.withPath("quantityOnHand").ignored()
                        )
                ));
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

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}