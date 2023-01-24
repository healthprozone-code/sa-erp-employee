package com.sa.erp.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sa.erp.entities.Position;
import com.sa.erp.services.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@WebMvcTest(PositionController.class)
//@ActiveProfiles(resolver = MyActiveProfilesResolver.class)
public class PositionControllerTest {

    private static final String URL="/api/position";

    @Autowired
    private PositionService positionService;

    private Position auxTestPosition;

    private String auxTestID;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void initialize(){
        this.auxTestPosition = this.positionService.savePosition(new Position("63cef5abf543871f44a318bc", "Dev", true, LocalDate.now(),LocalDate.now()));
        this.auxTestID = this.auxTestPosition.getId();

    }

    @Test
    public void testGetAllPosition() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get(URL.concat("/"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testPositionById() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get(URL.concat("/{id}"),this.auxTestID)
                        .param("id",this.auxTestID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSavePosition() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .post(URL.concat("/"))
                        .content(asJsonString(this.auxTestPosition))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdatePosition() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .put(URL.concat("/{id}"),this.auxTestID)
                        .content(asJsonString(this.auxTestPosition))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
            MvcResult mockResult = result.andReturn();

            MockHttpServletResponse response =mockResult.getResponse();

            log.debug(response.getContentAsString());
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDeletePosition() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .delete(URL.concat("/{id}"),this.auxTestID)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            MvcResult mockResult = result.andReturn();

            MockHttpServletResponse response =mockResult.getResponse();

            log.debug(response.getContentAsString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}




