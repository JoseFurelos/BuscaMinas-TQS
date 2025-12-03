package com.buscaminas.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buscaminas.model.Casella;
import com.buscaminas.model.Taulell;
import com.buscaminas.view.IVista;

@ExtendWith(MockitoExtension.class)
class JocControllerTest {

    @Mock
    Taulell taulellMock;

    @Mock
    IVista vistaMock;
    
    @Mock
    Casella casellaMock;

    JocController controller;

    @BeforeEach
    void setUp() {
        controller = new JocController(taulellMock, vistaMock);
    }

    @Test
    void testJugarTorn() {
        
        when(taulellMock.getCasella(0, 0)).thenReturn(casellaMock);

        
        controller.jugarTorn(0, 0);

        
        verify(taulellMock).getCasella(0, 0);
        verify(casellaMock).destapar(); 
        verify(vistaMock).mostrarTaulell();
    }
}