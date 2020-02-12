package org.cleanbank.cleanbank.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cleanbank.cleanbank.entity.Account;
import org.cleanbank.cleanbank.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.Matchers.containsString;
//import static org.junit.jupiter.api.AssertEquals.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(AccountController.class)
@Profile("test")
class MockMvc_AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    // To Map JSON object into Class Object
    ObjectMapper objectMapper;

    private List<Account> accountList;
    private Account account;

    //To Setup ALL The Objects


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllAccounts() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accounts")
                                            .content(MediaType.APPLICATION_JSON_VALUE)
                                            .accept(MediaType.APPLICATION_JSON_VALUE)
                                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                                     .andExpect(status().isOk())
                                     .andReturn();
        List<Account> actualList = new ArrayList<>();

        System.out.println(mvcResult.getResponse().getContentAsString());

        try{
            actualList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                    new TypeReference<List<Account>>() {
                    });
        }
        catch (Exception e){

        }

        assertEquals(0,actualList.size());


    }

    @Test
    void getAllAccountsInBranch() {
    }

    @Test
    void addNewAccount() {

    }

    @Test
    void editAccount() {
    }

    @Test
    void deleteAccountFromBranch() {
    }
}