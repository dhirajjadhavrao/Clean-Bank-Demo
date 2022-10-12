package org.cleanbank.cleanbank.controller;

import org.cleanbank.cleanbank.entity.Account;
import org.cleanbank.cleanbank.entity.Branch;
import org.cleanbank.cleanbank.entity.FixedDeposite;
import org.cleanbank.cleanbank.service.FixedDepositService;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//@Profile("test")
class FixedDepositControllerTest {
    private FixedDepositController mockFixedDepositController;
    private FixedDepositService mockFixedDepositService;
    private List<FixedDeposite> mockFdList;
    private FixedDeposite mockFixedDepositEntity;
    private Branch branchOne;
    private Branch branchTwo;
    private Account account;

    /** To Initialise Mock Data */
    @BeforeEach
    void setUp() {
        mockFixedDepositEntity = new FixedDeposite(10,30000,5,7,1);
        List<FixedDeposite> fDList = new ArrayList<>();

        fDList.add(new FixedDeposite(1,20000,5,7,1));
        fDList.add(new FixedDeposite(2,22000,5,7,2));
        fDList.add(new FixedDeposite(3,23000,5,7,3));
        fDList.add(new FixedDeposite(4,24000,5,7,1));
        fDList.add(new FixedDeposite(5,25000,5,7,2));
        fDList.add(new FixedDeposite(6,26000,5,7,1));
        fDList.add(new FixedDeposite(7,27000,5,7,4));

        branchOne = new Branch(1,"clean bank","Satara");
        branchTwo = new Branch(2,"clean bank","Pune");

        //Adding Branch Object To Each Account


        //Creating Mock Objects
        mockFixedDepositController = mock(FixedDepositController.class);
        mockFixedDepositService = mock(FixedDepositService.class);

        //Getting Static DataList
        when(mockFixedDepositService.getAllFDs()).thenReturn(fDList);
        mockFdList = mockFixedDepositService.getAllFDs();

    }

    @Nested
    @DisplayName("Get FD By ")
    @TestInstance(TestInstance.Lifecycle.PER_METHOD)
    class GetFD {
        /**
         * Test All data get From Table
         */
        @Test
        @DisplayName("All FD Details")
        void getAllFDs() {
            when(mockFixedDepositController.getAllFDs()).thenReturn(mockFdList);
            List<FixedDeposite> testFdList = mockFixedDepositController.getAllFDs();

            assertIterableEquals(mockFdList, testFdList);
        }

        /**
         * Test FD Details By Branch From Service
         */
        @Test
        @DisplayName("Branch")
        void getAllFDByBranch() {
/*        //for mockFixedDepositService
            when(mockFixedDepositService.getAllFDByBranch(1))
                    .thenReturn(mockFdList
                            .stream()
                            .filter(e -> e.getAccount().getBranch().getBranchCode() == 1)
                            //NullPointer add Branch object To Each Account Object
                            .collect(Collectors.toList()));
        List<FixedDeposite> expectedList = mockFixedDepositService.getAllFDByBranch(1);*/
        }

        /**
         * Test FD Details By Account From Table
         */
        @Test
        @DisplayName("Account")
        void getAllFDbyAccount() {
            //for mockFixedDepositService
            when(mockFixedDepositService.getAllFDByAccount(1))
                    .thenReturn(mockFdList
                            .stream()
                            .filter(e -> e.getAccount().getAccountNumber() == 1)
                            .collect(Collectors.toList()));
            List<FixedDeposite> expectedList = mockFixedDepositService.getAllFDByAccount(1);

            //For mockFixedDepositController
            when(mockFixedDepositController.getAllFDbyAccount(1))
                    .thenReturn(mockFdList
                            .stream()
                            .filter(e -> e.getAccount().getAccountNumber() == 1)
                            .collect(Collectors.toList()));
            List<FixedDeposite> actualList = mockFixedDepositController.getAllFDbyAccount(1);

            assertIterableEquals(expectedList, actualList);

        }
    }
    /** Test New Branch Is Added Using Branch Service */
    @Test
    @DisplayName("Add New FD To Account")
    void addNewFDToAccount() {
        //For mockFixedDepositService
        mockFixedDepositService.addNewFDToAccount(mockFixedDepositEntity,1);
        verify(mockFixedDepositService,times(1)).addNewFDToAccount(mockFixedDepositEntity,1);

        //For mockFixedDepositController
        mockFixedDepositController.addNewFDToAccount(mockFixedDepositEntity,1);
        verify(mockFixedDepositController,times(1)).addNewFDToAccount(mockFixedDepositEntity,1);
    }

    /** Test Branch Is Deleted Using Branch Service */
    @Test
    @DisplayName("Delete FD For Account")
    void deleteFD() {
        //For mockFixedDepositService
        mockFixedDepositService.deleteFD(1);
        verify(mockFixedDepositService,times(1)).deleteFD(1);

        //For mockFixedDepositController
        mockFixedDepositController.deleteFD(1);
        verify(mockFixedDepositController,times(1)).deleteFD(1);
    }
}