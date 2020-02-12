package org.cleanbank.cleanbank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cleanbank.cleanbank.entity.Branch;
import org.cleanbank.cleanbank.entity.Response;
import org.cleanbank.cleanbank.repository.BranchRepository;
import org.cleanbank.cleanbank.service.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BranchController.class)
@Profile("test")
class MockMvc_BranchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @MockBean
    private BranchRepository branchRepository;

    @InjectMocks
    private  BranchController branchController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    // To Map JSON object into Class Object
    ObjectMapper objectMapper;

    private List<Branch> branchList;
    private Branch branch;

    //To Setup ALL The Objects


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        objectMapper = new ObjectMapper();
    }

        @Test
    void getDisplayMessage() throws Exception {
          mockMvc.perform(
                get("/")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome in Bank\n" +
                        "For Branch Details /branch\n" +
                        "For All Accounts /branch/account\n" +
                        "For All Fixed Deposits /branch/fixeddeposit\n" +
                        "For transaction /branch/transactions")));
    }

    @Test
    void addNewBranch() throws Exception {
        Branch branch = new Branch();
        branch.setBranchCity("Washim");
        branch.setBranchName("Clean Bank");
        String jsonObject = objectMapper.writeValueAsString(branch);
        MvcResult mvcResult = mockMvc.perform(post("/branch")
                                        .content(jsonObject)
                                        .accept(MediaType.APPLICATION_JSON_VALUE)
                                        .contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(status().isOk())
                                        .andDo(print())
                                        .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());


    }

    @Test
    void updateBranchDetails() throws Exception {
        Branch branch = new Branch();
        branch.setBranchName("Clean Bank");
        branch.setBranchCity("Delhi");
        branch.setBranchCode(2);

        Response response = new Response();
        response.setStatus(Boolean.TRUE);
        branchRepository.save(branch);
        verify(branchRepository,times(1)).save(branch);

//        when(branchService.updateBranchDetails(branch)).thenReturn(response);
        branchService.updateBranchDetails(branch);
        verify(branchService,times(1)).updateBranchDetails(branch);

        ResponseEntity<?> responseFromService = branchService.updateBranchDetails(branch);
        System.out.println(responseFromService);

        String jsonObject = objectMapper.writeValueAsString(branch);

        MvcResult mvcResult = mockMvc.perform(put("/branch")
                                        .content(jsonObject)
                                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                                        .accept(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk())
//                                .andExpect(jsonPath("$.status").value("true"))
                                .andReturn();
        System.out.println(mvcResult.getResponse().getStatus());

    }

    @Test
    void getAllBranchDetails() throws Exception {

        when(branchRepository.findAll()).thenReturn(Arrays.asList(new Branch(1,"C Bank","Pune"),new Branch(1,"C Bank","Washim")));
        List<Branch> blist = (List<Branch>) branchRepository.findAll();

        when(branchService.getAllBranchDetails()).thenReturn(blist);


        MvcResult mvcResult = this.mockMvc.perform(get("/branch")
                                            .accept(MediaType.ALL)
                                            .content(MediaType.APPLICATION_JSON_VALUE))
                                    .andDo(print())
                                    .andExpect(status().isOk())
                                    .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getSelectedBranch() {
    }

    @Test
    void deleteSelectedBranch() {
    }
}