package com.app.playerservicejava.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PlayerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPlayersReturnsPlayersSortedAscending() throws Exception {
        mockMvc.perform(get("/v1/players")
                        .param("sortBy", "lastName")
                        .param("direction", "ASC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.players[0].lastName").value("Adams"))
                .andExpect(jsonPath("$.players[1].lastName").value("Yellow"))
                .andExpect(jsonPath("$.players[2].lastName").value("Zephyr"));
    }

    @Test
    void getPlayersReturnsPlayersSortedDescending() throws Exception {
        mockMvc.perform(get("/v1/players")
                        .param("sortBy", "lastName")
                        .param("direction", "DESC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.players[0].lastName").value("Zephyr"))
                .andExpect(jsonPath("$.players[1].lastName").value("Yellow"))
                .andExpect(jsonPath("$.players[2].lastName").value("Adams"));
    }

    @Test
    void getPlayersReturnsBadRequestForUnsupportedProperty() throws Exception {
        mockMvc.perform(get("/v1/players")
                        .param("sortBy", "unknownField"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.containsString("Invalid sort property")));
    }
}
