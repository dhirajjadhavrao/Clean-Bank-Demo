package org.cleanbank.cleanbank.service;

import org.cleanbank.cleanbank.entity.Account;
import org.cleanbank.cleanbank.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//@Profile("test")
@DisplayName("AccountService Test To ")
class AccountServiceTest {
    private Account mockAccount;
    private List<Account> mockAccountList;
    private AccountService mockAccountService;
    private AccountRepository mockAccountRepository;

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
        mockAccountRepository = mock(AccountRepository.class);
        mockAccountService = mock(AccountService.class);

        when(mockAccountRepository.findAll()).thenReturn(accountList);
        mockAccountList = (List<Account>) mockAccountRepository.findAll();
    }

    @Nested
    @DisplayName("Get Account Details BY ")
    class AccountDetails {
        /**
         * Method TO Get All Accounts By Branch
         */
        @Test
        @DisplayName("Branch")
        void getAllAccountsInBranch() {
            //For mockAccountRepository
            when(mockAccountRepository.findAllByBranchBranchCode(1))
                    .thenReturn(
                            mockAccountList
                                    .stream()
                                    .filter(e -> e.getBranch().getBranchCode() == 1)
                                    .collect(Collectors.toList()));
            List<Account> expectedList = mockAccountRepository.findAllByBranchBranchCode(1);

            //For mockAccountService
            when(mockAccountService.getAllAccountsInBranch(1))
                    .thenReturn(
                            mockAccountList
                                    .stream()
                                    .filter(e -> e.getBranch().getBranchCode() == 1)
                                    .collect(Collectors.toList()));
            List<Account> actualList = mockAccountService.getAllAccountsInBranch(1);

            assertIterableEquals(expectedList, actualList);

        }

        /**
         * Method To Get Selected Account Details
         */
        @Test
        @DisplayName("Selected Account")
        void getSelectedAccountDetails() {
            //For mockAccountRepository
            when(mockAccountRepository.findByAccountNumber(1))
                    .thenReturn(mockAccountList.get(0)
                        /*mockAccountList
                        .stream()
                        .filter(e -> e.getAccountNumber() == 1)
                        .findFirst()*/
                    );
            Account expected = mockAccountRepository.findByAccountNumber(1);

            //For mockAccountService
            when(mockAccountService.getSelectedAccountDetails(1))
                    .thenReturn(mockAccountList.get(0)
                  /*      mockAccountList
                        .stream()
                        .filter(e -> e.getAccountNumber() == 1)
                        .findFirst()*/
                    );
            Account actual = mockAccountService.getSelectedAccountDetails(1);

            assertEquals(expected, actual);
        }
    }

    /** Method TO Add New Account Into Database */
    @Test
    @DisplayName("Add New Account")
    void addNewAccount() {
        //For mockAccountRepository
        mockAccountRepository.save(mockAccount);
        verify(mockAccountRepository,times(1)).save(mockAccount);

        //For mockAccountService
        mockAccountService.addNewAccount(mockAccount,1);
        verify(mockAccountService,times(1)).addNewAccount(mockAccount,1);

    }

    /** Method TO Delete Account Details */
    @Test
    @DisplayName("Delete Account")
    void deleteAccountFromBranch() {
        //For mockAccountRepository
        mockAccountRepository.deleteById(1);
        verify(mockAccountRepository,times(1)).deleteById(1);

        //For mockAccountService
        mockAccountService.deleteAccountFromBranch(1);
        verify(mockAccountService,times(1)).deleteAccountFromBranch(1);

    }

    /** Method To Update Account Details */
    @Test
    @DisplayName("Update Account")
    void updateAccount() {
        //For mockAccountRepository
        mockAccountRepository.save(mockAccount);
        verify(mockAccountRepository,times(1)).save(mockAccount);

        //FOr mockAccountService
        mockAccountService.updateAccount(mockAccount,1);
        verify(mockAccountService,times(1)).updateAccount(mockAccount,1);

    }
}