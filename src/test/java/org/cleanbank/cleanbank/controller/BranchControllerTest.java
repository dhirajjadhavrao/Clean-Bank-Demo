package org.cleanbank.cleanbank.controller;

import org.cleanbank.cleanbank.entity.Branch;
import org.cleanbank.cleanbank.service.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@Profile("test")
@DisplayName("Test BranchController To")
class BranchControllerTest {
    private BranchService mockBranchService;
    private BranchController mockBranchController;
    private List<Branch> mockBranchList;
    private Branch mockBranch,testEntity;

    /** To Initialise Mock Data */
    @BeforeEach
    void setUp() {
        //To Create Mock Object
        mockBranchService = mock(BranchService.class);
        mockBranchController = mock(BranchController.class);

        //To Create Static data for Testing
        List<Branch> branchList = new ArrayList<>();
        branchList.add(new Branch(1,"s bank","pune"));
        branchList.add(new Branch(2,"s bank","satara"));
        branchList.add(new Branch(3,"s bank","nagpur"));
        branchList.add(new Branch(4,"s bank","nashik"));
        branchList.add(new Branch(5,"s bank","nagpur"));

        //To Test mockBranchService object
        when(mockBranchController.getAllBranchDetails()).thenReturn(branchList);
        mockBranchList = mockBranchController.getAllBranchDetails();

    }

    @Test
    @DisplayName("Check Main Display Message")
    void welcomeMessage() {
        //To Test Welcome Message
        when(mockBranchController.welcomeMessage()).thenReturn("Hi Tester");
        String testMsg = mockBranchController.welcomeMessage();

        assertEquals("Hi Tester",testMsg);

    }

    @Nested
    @DisplayName("Get Branch Details By")
    class GetBranchDetails {
        /**
         * Test All data get From Table
         */
        @Test
        @DisplayName("All Branch Details")
        void getAllBranchDetails() {
            //For MockBranchEntityController
            when(mockBranchController.getAllBranchDetails()).thenReturn(mockBranchList);
            List<Branch> testList = mockBranchController.getAllBranchDetails();

            assertEquals(mockBranchList, testList);
        }

        /**
         * Test Selected  BranchDetails From Table
         */
        @Test
        @DisplayName("Selected Branch Details")
        void getSelectedBranch() {
            //For MockBranchEntityService
            //when(mockBranchService.getSelectedBranchDetails(2)).thenReturn(mockBranchList.stream().filter(e -> e.getBranchCode() == 2).findAny());
            when(mockBranchService.getSelectedBranchDetails(2)).thenReturn(new Branch(2, "s bank", "satara"));
            mockBranch = mockBranchService.getSelectedBranchDetails(2);

            //For MockBranchEntityController
            when(mockBranchController.getSelectedBranch(2)).thenReturn(mockBranch);
            testEntity = mockBranchController.getSelectedBranch(2);

            assertEquals(mockBranch, testEntity);
        }
    }
    /** Test New Branch Is Added Using Branch Service */
    @Test
    @DisplayName("Add New Branch Details")
    void addNewBranch() {
        //For MockBranchEntityService
        mockBranchService.addNewBranch(mockBranch);
        verify(mockBranchService,times(1)).addNewBranch(mockBranch);

        //For MockBranchEntityController
        mockBranchController.addNewBranch(mockBranch);
        verify(mockBranchController,times(1)).addNewBranch(mockBranch);

    }

    /** Test New Branch Is Updated Using Branch Service */
    @Test
    @DisplayName("Update Branch Details")
    void editBranchDetails() {
        //For MockBranchEntityService
        mockBranchService.updateBranchDetails(mockBranch);
        verify(mockBranchService, times(1)).updateBranchDetails(mockBranch);

        //For MockBranchEntityController
        mockBranchController.updateBranchDetails(mockBranch);
        verify(mockBranchController, times(1)).updateBranchDetails(mockBranch);
    }

    /** Test Branch Is Deleted Using Branch Service */
    @Test
    @DisplayName("Delete Branch Details")
    void deleteSelectedBranch() {
        //For MockBranchEntityService
        mockBranchService.deleteSelectedBranch(2);
        verify(mockBranchService,times(1)).deleteSelectedBranch(2);

        //For MockBranchEntityController
        mockBranchController.deleteSelectedBranch(2);
        verify(mockBranchController, times(1)).deleteSelectedBranch(2);
    }
}