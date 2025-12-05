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
    IGeneradorMines generadorBuit = (x, y) -> false;

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
    
    @Test
    void testLoopInicialitzarCaselles() {
    	//Cas 0 iteracions
    	int mida = 0;
    	Taulell taulellZero = new Taulell(mida, generadorBuit);
    	assertEquals(0, taulellZero.getMida());
    	//excepcio si accedim a 0,0 ja que el bucle no ha creat res
    	assertThrows(IndexOutOfBoundsException.class, () -> { taulellZero.getCasella(0, 0); });
    	
    	//Cas 1 iteracio
    	mida = 1;
    	Taulell taulellUna = new Taulell(mida, generadorBuit);
    	assertEquals(1, taulellUna.getMida());
    	//Ara si ha d'existir la Casella 0,0
    	assertNotNull(taulellUna.getCasella(0, 0));
    	
    	
    	//Cas N iteracions
    	mida = 3;
    	Taulell taulellN = new Taulell(mida, generadorBuit);
    	assertEquals(3, taulellN.getMida());
    	assertNotNull(taulellN.getCasella(0, 0));
        assertNotNull(taulellN.getCasella(0, 1));
        assertNotNull(taulellN.getCasella(1, 1));
        assertNotNull(taulellN.getCasella(1, 0));
    }
}