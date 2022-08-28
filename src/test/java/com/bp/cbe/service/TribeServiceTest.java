package com.bp.cbe.service;

import com.bp.cbe.domain.dto.TribeDto;
import com.bp.cbe.domain.entities.TribeEntity;
import com.bp.cbe.exceptions.NotFoundException;
import com.bp.cbe.repository.TribeRepository;
import com.bp.cbe.service.impl.TribeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TribeServiceTest {

    TribeService tribeService;
    TribeRepository tribeRepositoryMock;

    @BeforeEach
    void setUp() {
        tribeRepositoryMock = Mockito.mock(TribeRepository.class);
        tribeService = new TribeServiceImpl(tribeRepositoryMock, null);
    }

    @Test
    void getById() {
        long id = 1;
        TribeEntity entity = new TribeEntity(id, "Empresas", 1, null, null);
        when(this.tribeRepositoryMock.findById(id)).thenReturn(Optional.of(entity));
        TribeDto tribeDto = tribeService.getById(id);
        assertNotNull(tribeDto);
        assertEquals(id, tribeDto.getId());
    }

    @Test
    void tribuInexistente() {
        long id = 1;
        when(this.tribeRepositoryMock.findById(id)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NotFoundException.class, () -> tribeService.getById(id));
        assertEquals("The tribe with id 1 does not exist.", exception.getMessage());
    }
}
