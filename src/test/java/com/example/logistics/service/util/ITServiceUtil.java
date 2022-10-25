package com.example.logistics.service.util;

import com.example.logistics.dto.CarDto;
import com.example.logistics.dto.DriverDto;
import com.example.logistics.dto.DriversLicenseDto;
import com.example.logistics.enums.DriverCategory;
import com.example.logistics.model.Driver;
import com.example.logistics.model.DriversLicense;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@Component
public class ITServiceUtil {
    @Autowired
    private ObjectMapper objectMapper;

    public <T> T getDtoFromMvcResult(MvcResult mvcResult, Class<T> expectedValueType) throws Exception {
        String contentAsString = mvcResult
                .getResponse()
                .getContentAsString();
        return objectMapper.readValue(contentAsString, expectedValueType);
    }

    public <T> T getEntityByIdFromRestApi(MockMvc mockMvc, ResultMatcher resultMatcher, Class<T> expectedValueType, String url) throws Exception {
        MvcResult getEntityDtoByIdExistResult = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(resultMatcher)
                .andReturn();
        return getDtoFromMvcResult(getEntityDtoByIdExistResult, expectedValueType);
    }

    public <T> T sendUpdateTestDto(MockMvc mockMvc, Object dto, Class<T> expectedValueType, String url) throws Exception {
        ResultActions resultAfterCreatedCar = mockMvc
                .perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)));

        return getDtoFromMvcResult(resultAfterCreatedCar.andReturn(), expectedValueType);
    }

    public  <T> T sendPostTestDto(MockMvc mockMvc, Object dto, Class<T> expectedValueType, String url) throws Exception {
        ResultActions resultAfterCreatedCar = mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)));
        return getDtoFromMvcResult(resultAfterCreatedCar.andReturn(), expectedValueType);
    }

    public boolean sendDeleteTestDto(MockMvc mockMvc, String url) throws Exception {
        ResultActions resultAfterCreatedCar = mockMvc
                .perform(delete(url));
        return resultAfterCreatedCar.andReturn().getResponse().getStatus() == 201;
    }

    public static DriverDto getTestDriverDto() {
        return DriverDto.builder()
                .firstName("Booba")
                .lastName("Hirotokai")
                .driversLicense(DriversLicenseDto.builder()
                        .dateOfExpired(LocalDate.of(2022, 8, 12))
                        .categories(List.of(DriverCategory.C))
                        .build())
                .build();
    }

    public static Driver getTestDriver() {
        return Driver.builder()
                .firstName("Booba")
                .lastName("Hirotokai")
                .driversLicense(DriversLicense.builder()
                        .dateOfExpired(LocalDate.of(2022, 8, 12))
                        .categories(List.of(DriverCategory.C))
                        .build())
                .build();
    }

    public static CarDto getTestCarDto() {
        return CarDto.builder()
                .category("B")
                .number("123pp[")
                .build();
    }

    public static CarDto getIncorrectTestCarDto() {
        return CarDto.builder()
                .number("eoiwooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                        "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                        "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo")
                .build();
    }

}
