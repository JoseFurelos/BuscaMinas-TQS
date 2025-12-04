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
        assertThrows(IndexOutOfBoundsException.class, () -> taulell.getCasella(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> taulell.getCasella(11, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> taulell.getCasella(0, 11));
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
    
    @Test
    void testComptarMinesVeines() {
        lenient().when(generadorMock.hiHaMina(0, 1)).thenReturn(true);
        lenient().when(generadorMock.hiHaMina(1, 0)).thenReturn(true);
        
        Taulell t = new Taulell(5, generadorMock);
        assertEquals(2, t.comptarMinesVeines(0, 0), "La casella 0,0 hauria de tenir 2 mines veïnes");
        assertEquals(1, t.comptarMinesVeines(1, 0), "La casella 1,0 hauria de tenir 1 mina veïna");
        assertEquals(0, t.comptarMinesVeines(4, 4), "La casella 4,4 no hauria de tenir mines");
    }

    @Test
    void testDestaparCasella() {
        
        lenient().when(generadorMock.hiHaMina(anyInt(), anyInt())).thenReturn(false);
        lenient().when(generadorMock.hiHaMina(3, 3)).thenReturn(true);
        
        Taulell t = new Taulell(5, generadorMock);

        t.destaparCasella(0, 0);

        assertTrue(t.getCasella(0, 0).isDestapada(), "La casella clicada s'ha d'obrir");
        assertTrue(t.getCasella(0, 1).isDestapada(), "La recursivitat hauria d'obrir la veïna 0,1");
        assertTrue(t.getCasella(1, 1).isDestapada(), "La recursivitat hauria d'obrir la veïna 1,1");
        assertFalse(t.getCasella(3, 3).isDestapada(), "La mina no s'hauria de destapar sola");
    }
}