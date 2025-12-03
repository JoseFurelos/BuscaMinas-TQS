package com.buscaminas.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GeneradorMinesTest {

    @Mock
    IGeneradorMines generadorMock; 

    @Test
    void testPosarMinesAmbMock() {
       
        when(generadorMock.hiHaMina(1, 1)).thenReturn(true);
        when(generadorMock.hiHaMina(0, 0)).thenReturn(false);

        assertTrue(generadorMock.hiHaMina(1, 1), "El mock hauria de dir que sí");
        assertFalse(generadorMock.hiHaMina(0, 0), "El mock hauria de dir que no");

        verify(generadorMock).hiHaMina(1, 1);
    }
}