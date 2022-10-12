package org.cleanbank.cleanbank.controller;

import org.cleanbank.cleanbank.entity.Account;
import org.cleanbank.cleanbank.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;
//@Profile("test")
@DisplayName("Test AccountController To Check ")
class AccountControllerTest {
    private Account mockAccount;
    private List<Account> mockAccountList;
    private AccountService mockAccountService;
    private AccountController mockAccountController;

    /** Method TO Initialize Static Data and Mock objects    */
    @BeforeEach
    void setUp() {
        mockAccount = new Account(10,"Ram","pune",100111,1);

        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account(1,"dhiraj","pune",100111,1));
        accountList.add(new Account(2,"suraj","pune",10111,1));
        accountList.add(new Account(3,"sumit","new sanghvi",12221,1));
        accountList.add(new Account(4,"ajit","banner",220111,1));
        accountList.add(new Account(5,"shubham","balewadi",78111,2));
        accountList.add(new Account(6,"tushar","banner",788111,2));
        accountList.add(new Account(7,"kiran","banner",347111,1));

        //Mock Objects
        mockAccountController = mock(AccountController.class);
        mockAccountService = mock(AccountService.class);

        //Getting Data From Service
        when(mockAccountService.getAllAccounts()).thenReturn(accountList);
        mockAccountList = mockAccountService.getAllAccounts();
    }

    /** Test MEthod TO Check Accounts In Same Branch */
    @Test
    @DisplayName("Get All Accounts In Branch")
    void getAllAccountsInBranch() {
        //For mockAccountService
        when(mockAccountService.getAllAccountsInBranch(1))
                .thenReturn(
                        mockAccountList
                                .stream()
                                .filter(e -> e.getBranch().getBranchCode() == 1)
                                .collect(Collectors.toList()));
        List<Account> expectedList = mockAccountService.getAllAccountsInBranch(1);

        //For mockAccountController
        when(mockAccountController.getAllAccountsInBranch(1))
                .thenReturn(
                        mockAccountList
                                .stream()
                                .filter(e -> e.getBranch().getBranchCode() == 1)
                                .collect(Collectors.toList())
                );
        List<Account> actualList = mockAccountController.getAllAccountsInBranch(1);

        System.out.println(expectedList);
        System.out.println(actualList);

        assertIterableEquals(expectedList,actualList);
    }

    /** Method TO Check New Account Is Added Or not */
    @Test
    @DisplayName("Add New Account Into Branch")
    void addNewAccount() {
        //For mockAccountService
        mockAccountService.addNewAccount(mockAccount,1);
        verify(mockAccountService,times(1)).addNewAccount(mockAccount,1);

        //For mockAccountController
        mockAccountController.addNewAccount(mockAccount,1);
        verify(mockAccountController,times(1)).addNewAccount(mockAccount,1);

    }

    /** Test TO Update Account Details */
    @Test
    @DisplayName("Update Account In Branch")
    void editAccount() {
        //For mockAccountService
        mockAccountService.updateAccount(mockAccount,1);
        verify(mockAccountService, times(1)).updateAccount(mockAccount,1);

        //For  mockAccountController
        mockAccountController.editAccount(mockAccount,1);
        verify(mockAccountController,times(1)).editAccount(mockAccount,1);
    }

    @Test
    @DisplayName("Delete Account From Branch")
    void deleteAccountFromBranch() {
        //For mockAccountService
        mockAccountService.deleteAccountFromBranch(1);
        verify(mockAccountService,times(1)).deleteAccountFromBranch(1);

        //For mockAccountController
        mockAccountController.deleteAccountFromBranch(1);
        verify(mockAccountController,times(1)).deleteAccountFromBranch(1);
    }
}