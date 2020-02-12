package org.cleanbank.cleanbank.service;

import org.cleanbank.cleanbank.entity.Account;
import org.cleanbank.cleanbank.entity.Transaction;
import org.cleanbank.cleanbank.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@Profile("test")
@DisplayName("Test Transactions Service To")
class TransactionServiceTest {

    private List<Transaction> mockTransactionList;
    private Transaction mockTransaction;
    private TransactionService mockTransactionService;
    private TransactionRepository mockTransactionRepository;
    private Account accountOne;
    private Account accountTwo;

    /** Method To Initialise Mock Objects And Crate Static Data For Testing */
    @BeforeEach
    void setUp() {
        //Mocking Objects
        mockTransactionRepository = mock(TransactionRepository.class);
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
        when(mockTransactionRepository.findAll()).thenReturn(transactionList);
        mockTransactionList = (List<Transaction>) mockTransactionRepository.findAll();

    }

    /** Method TO Test Data Get By Using Branch Code */

    @Test
    @DisplayName("Get By Branch Code")
    void getAllTransactionByBranch() {

/*      System.out.println(mockTransactionList);
        System.out.println(
                mockTransactionList
                .stream()
                .filter(e -> e.getAccount().getAccountNumber() == 1)
                .findFirst()
        );*/

        //For mockTransactionRepository
        when(mockTransactionRepository.findAllByAccountBranchBranchCode(1))
                .thenReturn(
                        mockTransactionList
                        .stream()
                        .filter(e -> e.getAccount().getBranch().getBranchCode() == 1)
                        .collect(Collectors.toList()));
        List<Transaction> expectedList = mockTransactionRepository.findAllByAccountBranchBranchCode(1);

        //For mockTransactionService
        when(mockTransactionService.getAllTransactionByBranch(1))
                .thenReturn(
                        mockTransactionList
                        .stream()
                        .filter(e -> e.getAccount().getBranch().getBranchCode() == 1)
                        .collect(Collectors.toList()));
        List<Transaction> actualList = mockTransactionService.getAllTransactionByBranch(1);

        /*System.out.println(expectedList);
        System.out.println(actualList); */
        assertIterableEquals(expectedList,actualList);
    }

    /** Method Test To Check Data By Account Number */
    @Test
    @DisplayName("Get By Account Number")
    void getAllTransactionByAccount() {
        //For mockTransactionRepository
        when(mockTransactionRepository.findAllByAccountAccountNumber(2))
                .thenReturn(
                        mockTransactionList
                                .stream()
                                .filter(e -> e.getAccount().getAccountNumber() == 2)
                                .collect(Collectors.toList()));
        List<Transaction> expectedList = mockTransactionRepository.findAllByAccountAccountNumber(2);

        //For mockTransactionService
        when(mockTransactionService.getAllTransactionByAccount(2))
                .thenReturn(
                        mockTransactionList
                                .stream()
                                .filter(e -> e.getAccount().getAccountNumber() == 2)
                                .collect(Collectors.toList()));
        List<Transaction> actualList = mockTransactionService.getAllTransactionByAccount(2);

//        System.out.println(expectedList);
//        System.out.println(actualList);
        assertIterableEquals(expectedList,actualList);

    }

    /** Test Method To Add New Transaction */
    @Test
    @DisplayName("Add New Transaction")
    void addNewTransaction() {
        //For mockTransactionRepository
        mockTransactionRepository.save(mockTransaction);
        verify(mockTransactionRepository,times(1)).save(mockTransaction);

        //For mockTransactionService
        mockTransactionService.addNewTransaction(mockTransaction,1,1);
        verify(mockTransactionService, times(1)).addNewTransaction(mockTransaction,1,1);
    }

    /** Method To Check Right Action is Perform Depend On TransactionType */
    @Test
    @DisplayName("Perform Transaction Required Process")
    void performTransaction() {
    }

    /** Method To Check Transaction Is Deleted From Data */
    @Test
    @DisplayName("Delete Transaction")
    void deleteTransaction() {
        //For mockTransactionRepository
        mockTransactionRepository.deleteById(1);
        verify(mockTransactionRepository,times(1)).deleteById(1);

        //For mockTransactionService
        mockTransactionService.deleteTransaction(1);
        verify(mockTransactionService,times(1)).deleteTransaction(1);
    }
}