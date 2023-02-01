package com.sa.erp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sa.erp.entities.Repo;
import com.sa.erp.services.RepoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnabledIfSystemProperty(named = "spring.profiles.using", matches = "DEV")
@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepoControllerTest {

    private static final String URL="/api/repo";

    @Autowired
    private RepoService repoService;

    private Repo auxTestRepo;

    private String auxTestID;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void initialize(){
        this.auxTestRepo = this.repoService.saveRepo(new Repo(null, "hubio","https://github.com/hubio",true, null, LocalDate.now(), LocalDate.now()));
        this.auxTestID = this.auxTestRepo.getId();
    }

    @Test
    public void testGetAllRepo() throws Exception {
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

    @Test
    public void testRepoById() throws Exception {
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
    public void testRepoByIdError() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .get(URL.concat("/{id}"),"dkd")
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
    public void testSaveRepo() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .post(URL.concat("/"))
                            .content(asJsonString(this.auxTestRepo))
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
    public void testSaveRepoError() throws Exception {
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
    public void testUpdateRepo() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .put(URL.concat("/{id}"),this.auxTestID)
                            .content(asJsonString(this.auxTestRepo))
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
    public void testUpdateRepoError() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .put(URL.concat("/{id}"),"sjs")
                            .content(asJsonString(this.auxTestRepo))
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
    public void testDeleteRepo() throws Exception {
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
    public void testDeleteRepoError() throws Exception {
        try{
            ResultActions result=mockMvc.perform( MockMvcRequestBuilders
                            .delete(URL.concat("/{id}"),"djd")
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
