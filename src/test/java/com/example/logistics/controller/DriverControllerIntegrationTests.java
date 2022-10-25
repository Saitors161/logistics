package com.example.logistics.controller;

import com.example.logistics.dto.DriverDto;
import com.example.logistics.error.ApiException;
import com.example.logistics.repository.DriverRepository;
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
public class DriverControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ITServiceUtil serviceUtil;

    @Autowired
    private DriverRepository driverRepository;

    @AfterEach
    private void deleteAll() {
        driverRepository.deleteAll();
    }

    private String mainUrlDrivers = "/api/v1/drivers";

    @Test
    void getDriverByIdNotExistTest() throws Exception {
        MvcResult driverByIdNotExistResult = mockMvc.perform(get(mainUrlDrivers + "/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
        ApiException data = serviceUtil.getDtoFromMvcResult(driverByIdNotExistResult, ApiException.class);
        Assertions.assertEquals("Driver with id = 11 not found", data.getMessage());
    }

    @Test
    void getDriverByIdExistTest() throws Exception {
        DriverDto driverPosted = serviceUtil.sendPostTestDto(mockMvc
                , ITServiceUtil.getTestDriverDto()
                , DriverDto.class
                , mainUrlDrivers);
        DriverDto driverGotByIdFromApp = serviceUtil.getEntityByIdFromRestApi(mockMvc
                , status().isOk()
                , DriverDto.class
                , mainUrlDrivers + "/" + driverPosted.getId());
        Assertions.assertEquals(driverPosted, driverGotByIdFromApp);
    }


    @Test
    void deleteDriverByIdExistTest() throws Exception {
        DriverDto driverPosted = serviceUtil.sendPostTestDto(mockMvc
                , ITServiceUtil.getTestDriverDto()
                , DriverDto.class
                , mainUrlDrivers);
        Assertions.assertTrue(serviceUtil.sendDeleteTestDto(mockMvc
                , mainUrlDrivers + "/" + driverPosted.getId()));
        ApiException apiException = serviceUtil.getEntityByIdFromRestApi(mockMvc
                , status().is4xxClientError()
                , ApiException.class
                , mainUrlDrivers + "/" + driverPosted.getId());
        Assertions.assertEquals("Driver with id = " + driverPosted.getId() + " not found", apiException.getMessage());
    }

    @Test
    void updateDriverTest() throws Exception {
        DriverDto driverPosted = serviceUtil.sendPostTestDto(mockMvc
                , ITServiceUtil.getTestDriverDto()
                , DriverDto.class
                , mainUrlDrivers);
        DriverDto newDriverData = ITServiceUtil.getTestDriverDto();
        newDriverData.setId(driverPosted.getId());
        newDriverData.setFirstName("Kon");
        newDriverData.setLastName("Echno");
        newDriverData.getDriversLicense().setId(driverPosted.getDriversLicense().getId());

        Assertions.assertEquals(newDriverData, serviceUtil.sendUpdateTestDto(mockMvc
                , newDriverData
                , DriverDto.class
                , mainUrlDrivers));
        DriverDto driverDtoInAppAfterUpdate = serviceUtil.getEntityByIdFromRestApi(mockMvc
                , status().isOk()
                , DriverDto.class
                , mainUrlDrivers + "/" + driverPosted.getId());
        Assertions.assertEquals(driverDtoInAppAfterUpdate, newDriverData);
    }


    @Test
    void updateDriverNotExistTest() throws Exception {
        DriverDto driverDto = ITServiceUtil.getTestDriverDto();
        driverDto.setId(1);
        ApiException apiException = serviceUtil.sendUpdateTestDto(mockMvc, driverDto, ApiException.class, mainUrlDrivers);
        Assertions.assertEquals("Driver with id = " + driverDto.getId() + " not found", apiException.getMessage());
    }

    @Test
    void updateDriverNullIdTest() throws Exception {
        DriverDto driverDto = ITServiceUtil.getTestDriverDto();
        ApiException apiException = serviceUtil.sendUpdateTestDto(mockMvc, driverDto, ApiException.class, mainUrlDrivers);
        Assertions.assertEquals("The given id must not be null!", apiException.getMessage());
    }


    //todo: add some integration tests
}
