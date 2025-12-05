package com.buscaminas.controller;

import com.buscaminas.model.Taulell;
import com.buscaminas.view.IVista;

public class JocController {
    private Taulell taulell;
    private IVista vista;

    public JocController(Taulell taulell, IVista vista) {
    	this.taulell = taulell;
        this.vista = vista;
    }

    public void jugarTorn(int x, int y) {
    	taulell.destaparCasella(x, y);
    	vista.mostrarTaulell();
    }
}