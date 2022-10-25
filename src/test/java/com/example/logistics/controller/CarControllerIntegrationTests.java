package com.example.logistics.controller;

import com.example.logistics.dto.CarDto;
import com.example.logistics.error.ApiException;
import com.example.logistics.repository.CarRepository;
import com.example.logistics.service.util.ITServiceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ITServiceUtil serviceUtil;
    @Autowired
    private CarRepository carRepository;

    private String mainUrlCars = "/api/v1/cars";

    @AfterEach
    private void deleteAll() {
        carRepository.deleteAll();
    }

    @Test
    void createCarWithIncorrectDataTest() throws Exception {
        ApiException apiException = serviceUtil.sendPostTestDto(mockMvc
                , ITServiceUtil.getIncorrectTestCarDto()
                , ApiException.class
                , mainUrlCars);
        Assertions.assertEquals(apiException.getMessage(), "Incorrect data for operation : [Cars category should not be empty, Max size for car number = 100 characters]");
    }

    @Test
    void createCarWithIncorrectCategoryTest() throws Exception {
        CarDto carDto = ITServiceUtil.getTestCarDto();
        carDto.setCategory("wer");
        ApiException apiException = serviceUtil.sendPostTestDto(mockMvc
                , carDto
                , ApiException.class
                , mainUrlCars);
        Assertions.assertEquals(apiException.getMessage(), "No enum constant com.example.logistics.enums.DriverCategory.wer");
    }


    @Test
    void getCarByIdNotExistTest() throws Exception {
        MvcResult getCarByIdNotExistResult = mockMvc.perform(get(mainUrlCars + "/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
        ApiException data = serviceUtil.getDtoFromMvcResult(getCarByIdNotExistResult, ApiException.class);
        Assertions.assertEquals("Car with id = 11 not found", data.getMessage());
    }

    @Test
    void getCarByIdExistTest() throws Exception {
        CarDto carPosted = serviceUtil.sendPostTestDto(mockMvc
                , ITServiceUtil.getTestCarDto()
                , CarDto.class
                , mainUrlCars);
        CarDto carGetByIdFromApp = serviceUtil.getEntityByIdFromRestApi(mockMvc
                , status().isOk()
                , CarDto.class
                , mainUrlCars + "/" + carPosted.getId());
        Assertions.assertEquals(carPosted, carGetByIdFromApp);
    }


    @Test
    void deleteCarByIdExistTest() throws Exception {
        CarDto carPosted = serviceUtil.sendPostTestDto(mockMvc
                , ITServiceUtil.getTestCarDto()
                , CarDto.class
                , mainUrlCars);
        Assertions.assertTrue(serviceUtil.sendDeleteTestDto(mockMvc, mainUrlCars + "/" + carPosted.getId()));
        ApiException apiException = serviceUtil.getEntityByIdFromRestApi(mockMvc
                , status().is4xxClientError()
                , ApiException.class
                , mainUrlCars + "/" + carPosted.getId());
        Assertions.assertEquals("Car with id = " + carPosted.getId() + " not found", apiException.getMessage());
    }

    @Test
    void updateCarTest() throws Exception {
        CarDto carPosted = serviceUtil.sendPostTestDto(mockMvc
                , ITServiceUtil.getTestCarDto()
                , CarDto.class
                , mainUrlCars);
        CarDto newCarData = ITServiceUtil.getTestCarDto();
        newCarData.setId(carPosted.getId());
        newCarData.setCategory("A");
        newCarData.setNumber("9878jjn");

        Assertions.assertEquals(newCarData, serviceUtil.sendUpdateTestDto(mockMvc, newCarData, CarDto.class, mainUrlCars));
        CarDto carDtoInAppAfterUpdate = serviceUtil.getEntityByIdFromRestApi(mockMvc
                , status().isOk(), CarDto.class, mainUrlCars + "/" + carPosted.getId());
        Assertions.assertEquals(carDtoInAppAfterUpdate, newCarData);
    }


    @Test
    void updateCarNotExistTest() throws Exception {
        CarDto carDto = ITServiceUtil.getTestCarDto();
        carDto.setId(1);
        ApiException apiException = serviceUtil.sendUpdateTestDto(mockMvc, carDto, ApiException.class, mainUrlCars);
        Assertions.assertEquals("Car with id = " + carDto.getId() + " not found", apiException.getMessage());
    }

    @Test
    void updateCarNullIdTest() throws Exception {
        ApiException apiException = serviceUtil.sendUpdateTestDto(mockMvc, serviceUtil.getTestCarDto(), ApiException.class, mainUrlCars);
        Assertions.assertEquals("The given id must not be null!", apiException.getMessage());
    }
    //todo: add some integration tests
}
