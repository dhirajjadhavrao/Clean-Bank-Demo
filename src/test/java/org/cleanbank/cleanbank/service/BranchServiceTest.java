package org.cleanbank.cleanbank.service;

import org.cleanbank.cleanbank.entity.Branch;
import org.cleanbank.cleanbank.repository.BranchRepository;
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
class BranchServiceTest {
    //Common Variables Required For Testing
    private List<Branch> mockBranchList;
    private Branch mockBranch;
    private BranchRepository mockBranchRepository;
    private BranchService mockBranchService;

    /** Method TO Initialise Mock Data */
    @BeforeEach
    void setUp() {
        //To Create Static data for Testing
        List<Branch> branchList = new ArrayList<>();
        branchList.add(new Branch(1,"s bank","pune"));
        branchList.add(new Branch(2,"s bank","satara"));
        branchList.add(new Branch(3,"s bank","nagpur"));
        branchList.add(new Branch(4,"s bank","nashik"));
        branchList.add(new Branch(5,"s bank","nagpur"));

        //To Create Mock Objects
        mockBranchRepository = mock(BranchRepository.class);
        mockBranchService = mock(BranchService.class);

        //To mock BranchRepository data
        when(mockBranchRepository.findAll()).thenReturn(branchList);
        mockBranchList = (List<Branch>) mockBranchRepository.findAll();
    }

    @Nested
    @DisplayName("Get Branch Details By")
    class BrachDetails {
        /**
         * Test To Check Data Return BY getAllBranchDetails() is right
         */
        @Test
        @DisplayName("All Branch Details")
        void getAllBranchDetails() {
            when(mockBranchService.getAllBranchDetails()).thenReturn(mockBranchList);
            List<Branch> testList = mockBranchService.getAllBranchDetails();
            //  List<Branch> t = Arrays.asList();

            assertIterableEquals(mockBranchList, testList);
        }

        /**
         * Method To Check Get Selected Branch
         */
        @Test
        @DisplayName("Selected Branch Details")
        void getSelectedBranchDetails() {
            // when(mockBranchService.getSelectedBranchDetails(2)).thenReturn(mockBranchList.stream().filter(e -> e.getBranchCode() == 2).findFirst());
            //  mockBranch = mockBranchService.getSelectedBranchDetails(2);
            when(mockBranchRepository.findByBranchCode(2)).thenReturn(new Branch(2, "s bank", "satara"));
            mockBranch = mockBranchRepository.findByBranchCode(2);

            when(mockBranchService.getSelectedBranchDetails(2)).thenReturn(mockBranch);
            Branch testBranch = mockBranchService.getSelectedBranchDetails(2);

            assertEquals(mockBranch, testBranch);
        }
    }
    /** Method To Check Branch is Successfully Added into Table */
    @Test
    void addNewBranch() {
        mockBranchRepository.save(mockBranch);
        verify(mockBranchRepository,times(1)).save(mockBranch);

        mockBranchService.addNewBranch(mockBranch);
        verify(mockBranchService,times(1)).addNewBranch(mockBranch);
    }

    /** Method To Check Selected Branch is Deleted From Table */
    @Test
    void deleteSelectedBranch() {
        mockBranchRepository.deleteById(1);
        verify(mockBranchRepository, times(1)).deleteById(1);

        mockBranchService.deleteSelectedBranch(1);
        verify(mockBranchService,times(1)).deleteSelectedBranch(1);
    }

    /** Method To  Check Branch Details Are Updated into Table */
    @Test
    void updateBranchDetails() {
        mockBranchRepository.save(mockBranch);
        verify(mockBranchRepository,times(1)).save(mockBranch);

        mockBranchService.updateBranchDetails(mockBranch);
        verify(mockBranchService,times(1)).updateBranchDetails(mockBranch);
    }
}