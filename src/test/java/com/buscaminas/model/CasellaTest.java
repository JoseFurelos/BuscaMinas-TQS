package com.buscaminas.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CasellaTest {

    @Test
    void testCasellaInicialmentTapada() {
        Casella casella = new Casella();
        assertFalse(casella.isDestapada());
    }
    
    @Test
    void testDestaparCasella() {
        Casella casella = new Casella();
        casella.destapar();
        assertTrue(casella.isDestapada());
    }
}