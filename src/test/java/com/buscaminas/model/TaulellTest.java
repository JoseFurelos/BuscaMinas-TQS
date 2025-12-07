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
    
    void testComptarMinesVeines() {
        lenient().when(generadorMock.hiHaMina(0, 1)).thenReturn(true);
        lenient().when(generadorMock.hiHaMina(1, 0)).thenReturn(true);
        
        Taulell t = new Taulell(5, generadorMock);
        assertEquals(1, t.comptarMinesVeines(0, 1), "La casella 0,1 hauria de tenir 1 mina vena");
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
    
    @Test 
    void testHeGuanyat(){
    	
    	//mines nomes a la posicio 0,0
    	IGeneradorMines generadorMock2 = (x,y) -> (x==0 && y==0);
    	
    	Taulell t = new Taulell(2,generadorMock2);
    	
    	t.destaparCasella(0,1);
    	assertFalse(t.heGuanyat());
    	
    	t.destaparCasella(1,0);
    	t.destaparCasella(1,1);
    	
    	assertTrue(t.heGuanyat());
    }
    
    
    @Test
    void testToggleBandera_PosarITreure() {
        Taulell t = new Taulell(5, (x, y) -> false);
        t.toggleBandera(0, 0);
        assertTrue(t.getCasella(0, 0).isFlag());
        t.toggleBandera(0, 0);
        assertFalse(t.getCasella(0, 0).isFlag());
    }

    @Test
    void testToggleBandera_NoFuncionaSiEstaOberta() {
        Taulell t = new Taulell(5, (x, y) -> false);
        t.getCasella(1, 1).destapar();
        t.toggleBandera(1, 1);
        assertFalse(t.getCasella(1, 1).isFlag());
    }

    @Test
    void testToggleBandera_ForaDeLimits() {
        Taulell t = new Taulell(5, (x, y) -> false);

        assertDoesNotThrow(() -> {
            t.toggleBandera(-1, 0);
            t.toggleBandera(0, 100);
        });
    }
}