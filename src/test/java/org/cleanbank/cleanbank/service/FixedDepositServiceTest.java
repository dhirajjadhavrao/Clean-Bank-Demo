package org.cleanbank.cleanbank.service;

import org.cleanbank.cleanbank.entity.FixedDeposite;
import org.cleanbank.cleanbank.repository.FixedDepositRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
@Profile("test")
class FixedDepositServiceTest {

    private List<FixedDeposite> mockFixedDepositList;
    private FixedDeposite mockFixedDepositEntity;
    private FixedDepositService mockFixedDepositService;
    private FixedDepositRepository mockFixedDepositRepository;

    /** MEthod TO Initialize Static Data and Mock objects    */
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

        //Creating Mock Objects
        mockFixedDepositRepository = mock(FixedDepositRepository.class);
        mockFixedDepositService = mock(FixedDepositService.class);

        //Method Call To Get All Static Data Form Repository
        when(mockFixedDepositRepository.findAll()).thenReturn(fDList);

        //Method To Get All Static Data Into MockList
        mockFixedDepositList = (List<FixedDeposite>) mockFixedDepositRepository.findAll();

    }

    /** Method TO Get All Fixed Deposit By Branch */
    @Test
    void getAllFDByBranch() {
    }

    /** Method TO Get All Fixed Deposit By Account */
    @Test
    void getAllFDByAccount() {
    }

    /** Method To Save New FD Into Table */
    @Test
    void addNewFDToAccount() {
       // for mockFixedDepositRepository
        mockFixedDepositRepository.save(mockFixedDepositEntity);
        verify(mockFixedDepositRepository, times(1)).save(mockFixedDepositEntity);

        //For mockFixedDepositService
        mockFixedDepositService.addNewFDToAccount(mockFixedDepositEntity,1);
        verify(mockFixedDepositService,times(1)).addNewFDToAccount(mockFixedDepositEntity,1);

    }

    /** Mothod To Delete FD   */
    @Test
    void deleteFD() {
        // for mockFixedDepositRepository
        mockFixedDepositRepository.deleteById(1);
        verify(mockFixedDepositRepository,times(11)).deleteById(1);

        //For mockFixedDepositService
        mockFixedDepositService.deleteFD(1);
        verify(mockFixedDepositService,times(1)).deleteFD(1);
    }
}