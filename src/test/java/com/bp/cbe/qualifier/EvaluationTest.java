package com.bp.cbe.qualifier;

import com.bp.cbe.domain.dto.OrganizationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class EvaluationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("create a organization")
    void createOrganizationShouldStoreCorrectly() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/organizations").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new OrganizationDto("Nueva")))).andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(greaterThan(0)))).andExpect(jsonPath("$.name").exists()).andExpect(jsonPath("$.tribes").doesNotExist()).andReturn();

        OrganizationDto organizationDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), OrganizationDto.class);

        mvc.perform(MockMvcRequestBuilders.get("/organizations/" + organizationDto.getId()).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.name", is("Nueva"))).andExpect(jsonPath("$.tribes").isEmpty());
    }

    @Test
    void updateOrganizationShouldStoreCorrectly() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/organizations").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new OrganizationDto("Actualizar")))).andExpect(status().isCreated()).andReturn();

        OrganizationDto nueva = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), OrganizationDto.class);

        mvc.perform(MockMvcRequestBuilders.put("/organizations/" + nueva.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new OrganizationDto("Actualizada")))).andExpect(status().isOk()).andReturn();

        mvc.perform(MockMvcRequestBuilders.get("/organizations/" + nueva.getId()).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.name", is("Actualizada"))).andExpect(jsonPath("$.tribes").exists());
    }

    @Test
    void getAllOrganizationsShouldReturnAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/organizations").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new OrganizationDto("Todas")))).andExpect(status().isCreated()).andReturn();

        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/organizations").contentType(MediaType.APPLICATION_JSON)).andDo(print());

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.length()", is(greaterThan(0))));
        result.andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    void deleteOrganizationShouldReturnIDDeletedRecord() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/organizations").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new OrganizationDto("Eliminar")))).andExpect(status().isCreated()).andReturn();

        OrganizationDto nueva = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), OrganizationDto.class);

        mvc.perform(MockMvcRequestBuilders.delete("/organizations/" + nueva.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();

        mvc.perform(MockMvcRequestBuilders.get("/organizations/" + nueva.getId()).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().is4xxClientError()).andExpect(jsonPath("$.message", is("The organization with id " + nueva.getId() + " does not exist.")));
    }

    @Test
    void getMetricsShouldReturnTribeNotFound() throws Exception {
        long id = 11111;
        mvc.perform(MockMvcRequestBuilders.get("/reports/repositories/" + id).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().is4xxClientError()).andExpect(jsonPath("$.message", is("The tribe with id " + id + " does not exist.")));
    }
}
