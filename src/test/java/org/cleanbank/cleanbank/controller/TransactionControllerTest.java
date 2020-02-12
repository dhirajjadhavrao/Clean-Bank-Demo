package org.cleanbank.cleanbank.controller;

import org.cleanbank.cleanbank.entity.Account;
import org.cleanbank.cleanbank.entity.Transaction;
import org.cleanbank.cleanbank.service.TransactionService;
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
@Profile("test")
@DisplayName("Test Transactions On ")
class TransactionControllerTest {

    private List<Transaction> mockTransactionList;
    private Transaction mockTransaction;
    private TransactionService mockTransactionService;
    private TransactionController mockTransactionController;
    private Account accountOne;
    private Account accountTwo;

    /** Method To Initialise Mock Objects And Crate Static Data For Testing */
    @BeforeEach
    void setUp() {
        //Mocking Objects
        mockTransactionController = mock(TransactionController.class);
        mockTransactionService = mock(TransactionService.class);

        //Cretaing Static Account Details
        accountOne = new Account(1,"dhiraj","satara",100002,1);
        accountTwo = new Account(2,"Suraj","Pune",200993,2);
        //Crating Static Data For Transaction
        mockTransaction = new Transaction(1,"credit",10000,1);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1,"credit",10000,1));
        transactionList.add(new Transaction(2,"debit",10000,1));
        transactionList.add(new Transaction(3,"credit",10000,2));
        transactionList.add(new Transaction(4,"debit",10000,1));
        transactionList.add(new Transaction(5,"debit",10000,2));
        transactionList.add(new Transaction(6,"credit",10000,1));
        transactionList.add(new Transaction(7,"credit",10000,2));
        transactionList.add(new Transaction(8,"credit",10000,1));

        //Adding Account To Transaction
        //For accountNumber == 1
        transactionList
                .stream()
                .filter(e -> e.getAccount().getAccountNumber() == 1)
                .map(e -> e.setAccountEntity(accountOne))
                .collect(Collectors.toList());
        //For accountNumber == 2
        transactionList
                .stream()
                .filter(e -> e.getAccount().getAccountNumber() == 2)
                .map(e -> e.setAccountEntity(accountTwo))
                .collect(Collectors.toList());
        //Getting Static Data
        when(mockTransactionService.getAllTransactions()).thenReturn(transactionList);
        mockTransactionList = (List<Transaction>) mockTransactionService.getAllTransactions();

    }

    @Nested
    @DisplayName("Get Transaction By")
    class GetTransaction{
    /** Method To Test All Transactions By Branch Code  */
    @Test
    @DisplayName("Branch Code")
    void getAllTransactionByBranch() {
        //For mockTransactionService
        when(mockTransactionService.getAllTransactionByBranch(1))
                .thenReturn(
                        mockTransactionList
                                .stream()
                                .filter(e -> e.getAccount().getBranch().getBranchCode() == 1)
                                .collect(Collectors.toList()));
        List<Transaction> expectedList = mockTransactionService.getAllTransactionByBranch(1);

        //For mockTransactionController
        when(mockTransactionController.getAllTransactionByBranch(1))
                .thenReturn(
                        mockTransactionList
                        .stream()
                        .filter(e -> e.getAccount().getBranch().getBranchCode() == 1)
                        .collect(Collectors.toList()));
        List<Transaction> actualList = mockTransactionController.getAllTransactionByBranch(1);

        assertIterableEquals(expectedList,actualList);
    }

    /** Method To TEst Get All Transactions By Account Numbers */
    @Test
    @DisplayName("Account Number")
    void getAllTransactionByAccount() {
        //For mockTransactionService
        when(mockTransactionService.getAllTransactionByAccount(2))
                .thenReturn(
                        mockTransactionList
                        .stream()
                        .filter(e -> e.getAccount().getAccountNumber() == 2)
                        .collect(Collectors.toList()));
        List<Transaction> expectedList = mockTransactionService.getAllTransactionByAccount(2);

        //For mockTransactionController
        when(mockTransactionController.getAllTransactionByAccount(2))
                .thenReturn(
                        mockTransactionList
                        .stream()
                        .filter(e -> e.getAccount().getAccountNumber() == 2)
                        .collect(Collectors.toList()));
        List<Transaction> actualList = mockTransactionController.getAllTransactionByAccount(2);

        assertIterableEquals(expectedList,actualList);
    }
    }
    /** Test Method To Add New Transaction */
    @Test
    @DisplayName("To Add New Transaction")
    void addNewTransaction() {
        //For mockTransactionService
        mockTransactionService.addNewTransaction(mockTransaction,1,1);
        verify(mockTransactionService,times(1)).addNewTransaction(mockTransaction,1,1);

        //For mockTransactionController
        mockTransactionController.addNewTransaction(mockTransaction,1,1);
        verify(mockTransactionController,times(1)).addNewTransaction(mockTransaction,1,1);

    }

    /** Method To Delete Transactions */
    @Test
    @DisplayName("Delete By Id")
    void deleteTransaction() {
        //For mockTransactionService
        mockTransactionService.deleteTransaction(1);
        verify(mockTransactionService,times(1)).deleteTransaction(1);

        //For mockTransactionController
        mockTransactionController.deleteTransaction(1);
        verify(mockTransactionController, times(1)).deleteTransaction(1);
    }
}