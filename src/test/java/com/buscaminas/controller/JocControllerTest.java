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
    
    MockGestorRank rankMock;
    JocController controlador = new JocController(taulellMock, vistaMock, rankMock);

    @BeforeEach
    void setUp() {
    	rankMock = new MockGestorRank();
        controlador = new JocController(taulellMock, vistaMock, rankMock);
    }

    @Test
    void testJugarTorn_GameOver() {
    	
    	Casella c = new Casella();
    	c.setMina(true);
    	when(taulellMock.getCasella(0, 0)).thenReturn(c);
        
        controlador.jugarTorn(0, 0);
        //destapem la mina
        verify(taulellMock).destaparCasella(0, 0);
        //mostrem derrota
        verify(vistaMock).mostrarGameOver();
        //ni actualitzem el taulell ni guardem puntuacio
        verify(vistaMock, never()).mostrarTaulell(); 
        assertFalse(rankMock.sHaCridatGuardar, "No s'ha de guardar puntuació si perds");
    }
    
    @Test
    void testJugarTorn_Safe() {
    	Casella c = new Casella();
    	c.setMina(false);
    	when(taulellMock.getCasella(1, 1)).thenReturn(c);
        when(taulellMock.heGuanyat()).thenReturn(false);
        
        controlador.jugarTorn(1, 1);
        
        verify(taulellMock).destaparCasella(1, 1);
        //no es mostra ni gameOver ni Victoria
        verify(vistaMock, never()).mostrarGameOver();
        verify(vistaMock, never()).mostrarGuanyador();
        //actualiztem la vista del taulell
        verify(vistaMock).mostrarTaulell();
        //pero no el ranking
        assertFalse(rankMock.sHaCridatGuardar);
        
    }
    
    @Test
    void testJugarTorn_Victoria() {
    	Casella c = new Casella();
    	c.setMina(false);
    	when(taulellMock.getCasella(2, 2)).thenReturn(c);
        when(taulellMock.heGuanyat()).thenReturn(true);
        
        controlador.jugarTorn(2, 2);
        
        verify(taulellMock).destaparCasella(2, 2);
        //mostem la victoria
        verify(vistaMock).mostrarGuanyador();
        //actualitzem la vista
        verify(vistaMock).mostrarTaulell();
    }
    
    @Test
    public void testEsGuardaPuntuacioEnGuanyar() {
        controlador.partidaGuanyada("jugador", 120);

        assertTrue(rankMock.sHaCridatGuardar);
        assertEquals(120, rankMock.ultimTempsGuardat);
    }
    
    @Test
    void testPosarBandera() {
        controlador.posarBandera(2, 3);
        verify(taulellMock).toggleBandera(2, 3);
        verify(vistaMock).mostrarTaulell();
    }
}