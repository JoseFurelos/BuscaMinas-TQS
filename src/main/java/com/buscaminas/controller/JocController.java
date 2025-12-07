package com.buscaminas.controller;

import com.buscaminas.model.*;
import com.buscaminas.view.IVista;

public class JocController {
    private Taulell taulell;
    private IVista vista;
    private IGestorRank rank;
    
    public JocController(Taulell taulell, IVista vista, IGestorRank rank) {
    	this.taulell = taulell;
        this.vista = vista;
        this.rank = rank;
    }

    public void jugarTorn(int x, int y) {
    	Casella c = taulell.getCasella(x, y);

	    if (c.isMina()) {
	        taulell.destaparCasella(x, y);
	        vista.mostrarGameOver();
	        return;
	    }

	    taulell.destaparCasella(x, y);
	    
	    if (taulell.heGuanyat()) {
	        vista.mostrarGuanyador();
	        rank.guardarPuntuacio("Jugador", 100);
	    }

	    vista.mostrarTaulell();	    
}
    
    public void partidaGuanyada(int temps) {
        rank.guardarPuntuacio("Jugador", temps);
    }
    public void posarBandera(int x, int y) {
        
    }
    
}