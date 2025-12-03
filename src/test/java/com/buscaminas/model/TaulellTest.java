package com.buscaminas.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

class TaulellTest {

    @Mock
    IGeneradorMines generadorMock;

    @Test
    void testCrearTaulell() {
        
        Taulell taulell = new Taulell(10, generadorMock);
        assertNotNull(taulell);
        assertEquals(10, taulell.getMida());
    }

    @Test
    void testGetCasellaLimits() {
        Taulell taulell = new Taulell(10, generadorMock);
        assertThrows(IndexOutOfBoundsException.class, () -> taulell.getCasella(-1, 0));
    }

    @Test
    void testInicialitzacioDeMines() {
        
        when(generadorMock.hiHaMina(0, 0)).thenReturn(true);
        when(generadorMock.hiHaMina(2, 2)).thenReturn(true);
        
        Taulell t = new Taulell(5, generadorMock);

        assertTrue(t.getCasella(0, 0).isMina(), "Hauria d'haver mina a 0,0");
        assertTrue(t.getCasella(2, 2).isMina(), "Hauria d'haver mina a 2,2");
        assertFalse(t.getCasella(0, 1).isMina(), "No hauria d'haver mina a 0,1");

        verify(generadorMock, times(25)).hiHaMina(anyInt(), anyInt());
    }
}