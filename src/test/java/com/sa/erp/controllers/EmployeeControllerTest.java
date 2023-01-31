package com.sa.erp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sa.erp.entities.Employee;
import com.sa.erp.entities.Position;
import com.sa.erp.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.Month;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    private static final String URL="/api/employee";

    @Autowired
    private EmployeeService employeeService;

    private Employee auxTestEmployee;

    private String auxTestID;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void initialize(){
        this.auxTestEmployee = this.employeeService.saveEmployee(new Employee("id","ana", "lopez", "2288554499", "ana@gmail.com", LocalDate.of(1995, Month.AUGUST,2),
                "@lopezA", LocalDate.of(2022,Month.JULY,15), true, null,false,
                new Position("id","name", true, LocalDate.now(), LocalDate.now()), LocalDate.now(),LocalDate.now()));
        this.auxTestID = this.auxTestEmployee.getId();
    }

    @Test
    public void testGetAllEmployee() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .get(URL.concat("/"))
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

    /*@Test
    public void testGetAllEmployeeError() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .get(URL.concat("/"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            MvcResult mockResult = result.andReturn();
            MockHttpServletResponse response =mockResult.getResponse();
            log.debug(response.getContentAsString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Test
    public void testEmployeeById() throws Exception {

        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .get(URL.concat("/{id}"),this.auxTestID)
                            .param("id",this.auxTestID)
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
    public void testEmployeeByIdError() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .get(URL.concat("/{id}"),"gf")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
            MvcResult mockResult = result.andReturn();

            MockHttpServletResponse response =mockResult.getResponse();

            log.debug(response.getContentAsString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEmployeeByFirstNameAndLastName() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .get(URL.concat("/{firstname}/{lastname}"),"ana","lopez")
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
    public void testEmployeeByFirstNameAndLastNameError() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .get(URL.concat("/{firstname}/{lastname}"),"fr","fr")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
            MvcResult mockResult = result.andReturn();

            MockHttpServletResponse response =mockResult.getResponse();

            log.debug(response.getContentAsString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveEmployee() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .post(URL.concat("/"))
                            .content(asJsonString(this.auxTestEmployee))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
            MvcResult mockResult = result.andReturn();

            MockHttpServletResponse response =mockResult.getResponse();

            log.debug(response.getContentAsString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveEmployeeError() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .post(URL.concat("/"))
                            .content("")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
            MvcResult mockResult = result.andReturn();

            MockHttpServletResponse response =mockResult.getResponse();

            log.debug(response.getContentAsString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .put(URL.concat("/{id}"),this.auxTestID)
                            .content(asJsonString(this.auxTestEmployee))
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
    public void testUpdateEmployeeError() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .put(URL.concat("/{id}"),"fs")
                            .content(asJsonString(this.auxTestEmployee))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
            MvcResult mockResult = result.andReturn();

            MockHttpServletResponse response =mockResult.getResponse();

            log.debug(response.getContentAsString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteEmployee() throws Exception {
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

    @Test
    public void testDeleteEmployeeError() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .delete(URL.concat("/{id}"),"fjr")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
            MvcResult mockResult = result.andReturn();

            MockHttpServletResponse response =mockResult.getResponse();

            log.debug(response.getContentAsString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetNextBirthdayPersonWithMongo() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .get(URL.concat("/nextbirthday"))
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

    /*@Test
    public void testGetNextBirthdayPersonWithMongoError() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .get(URL.concat("/nextbirthday"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            MvcResult mockResult = result.andReturn();

            MockHttpServletResponse response =mockResult.getResponse();

            log.debug(response.getContentAsString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/

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
