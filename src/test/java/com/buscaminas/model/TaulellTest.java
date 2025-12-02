package com.buscaminas.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TaulellTest {

    @Test
    void testCrearTaulell() {
        int mida = 10;
        Taulell taulell = new Taulell(mida);
        
        assertNotNull(taulell);
        assertEquals(10, taulell.getMida());
    }
    
    @Test
    void testGetCasellaLimits() {
        Taulell taulell = new Taulell(10);

        //Valors Frontera
        assertDoesNotThrow(() -> taulell.getCasella(0, 0));
        assertDoesNotThrow(() -> taulell.getCasella(9, 9));
        assertThrows(IndexOutOfBoundsException.class, () -> taulell.getCasella(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> taulell.getCasella(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> taulell.getCasella(10, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> taulell.getCasella(0, 10));
    }
}