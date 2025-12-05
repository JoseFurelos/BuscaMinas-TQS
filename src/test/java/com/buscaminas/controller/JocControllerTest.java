package com.buscaminas.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buscaminas.model.*;
import com.buscaminas.view.IVista;


@ExtendWith(MockitoExtension.class)
class JocControllerTest {

    @Mock
    Taulell taulellMock;

    @Mock
    IVista vistaMock;
    
    @Mock
    MockGestorRank rankMock = new MockGestorRank();
    JocController controlador = new JocController(taulellMock, vistaMock, rankMock);

    @BeforeEach
    void setUp() {
        controlador = new JocController(taulellMock, vistaMock, rankMock);
    }

    @Test
    void testJugarTorn() {
    	
        controlador.jugarTorn(0, 0);
        
        verify(taulellMock).destaparCasella(0, 0); 
        verify(vistaMock).mostrarTaulell();
    }
    
    @Test
    public void testEsGuardaPuntuacioEnGuanyar() {
        controlador.partidaGuanyada(120);

        assertTrue(rankMock.sHaCridatGuardar);
        assertEquals(120, rankMock.ultimTempsGuardat);
    }
}