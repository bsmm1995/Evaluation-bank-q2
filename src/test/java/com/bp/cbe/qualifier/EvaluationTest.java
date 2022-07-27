package com.bp.cbe.qualifier;

import com.bp.cbe.domain.dto.OrganizationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class EvaluationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createOrganizationShouldStoreCorrectly() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/organizations").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new OrganizationDto("Nueva")))).andExpect(status().isCreated()).andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.name").exists()).andExpect(jsonPath("$.tribes").doesNotExist()).andReturn();

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

        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/organizations").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.length()").value(2));
        result.andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    void deleteOrganizationShouldReturnIDDeletedRecord() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/organizations").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new OrganizationDto("Eliminar")))).andExpect(status().isCreated()).andReturn();

        OrganizationDto nueva = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), OrganizationDto.class);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete("/organizations/" + nueva.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        String id = result.getResponse().getContentAsString();

        assertNotNull(id);
        assertEquals(id, String.valueOf(nueva.getId()));

        mvc.perform(MockMvcRequestBuilders.get("/organizations/" + id).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().is4xxClientError()).andExpect(jsonPath("$.message", is("The organization with id " + id + " does not exist.")));
    }

    @Test
    void getMetricsShouldReturnTribeNotFound() throws Exception {
        long id = 11111;
        mvc.perform(MockMvcRequestBuilders.get("/reports/repositories/" + id).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().is4xxClientError()).andExpect(jsonPath("$.message", is("The tribe with id " + id + " does not exist.")));
    }
}
