package com.buscaminas;

import java.util.List;

import com.buscaminas.controller.JocController;
import com.buscaminas.model.*;
import com.buscaminas.view.VistaConsola;

public class Main {

	public static void main(String[] args) {
		
        int mida = 8; 

        IGeneradorMines generador = new GeneradorMines();

        Taulell taulell = new Taulell(mida, generador);

        VistaConsola vista = new VistaConsola(taulell);

        IGestorRank gestorRank = new GestorRankFitxer(); 

        JocController controller = new JocController(taulell, vista, gestorRank);
        
        System.out.println("BENVINGUT AL PESCAMINES!");
        vista.mostrarTaulell();

        boolean jocAcabat = false;

        while (!jocAcabat) {
            String[] inputs = vista.demanarCoordenades();
            
            if (inputs.length < 2) {
                System.out.println("Input invàlid. Torna-hi.");
                continue;
            }

            int fila, col;
            boolean esBandera = false;

            try {
                if (inputs[0].equalsIgnoreCase("F")) {
                    esBandera = true;
                    fila = Integer.parseInt(inputs[1]);
                    col = Integer.parseInt(inputs[2]);
                } 
                else {
                    fila = Integer.parseInt(inputs[0]);
                    col = Integer.parseInt(inputs[1]);
                }
            } catch (Exception e) {
                System.out.println("Has d'escriure números vàlids (ex: 0 1 o F 0 1).");
                continue;
            }
            if (fila < 0 || fila >= mida || col < 0 || col >= mida) {
                System.out.println("Fora de límits!");
                continue;
            }
            if (esBandera) {
                controller.posarBandera(fila, col);
                continue; 
            } else {
                controller.jugarTorn(fila, col);
            }
            Casella c = taulell.getCasella(fila, col);
            if (c.isMina() && c.isDestapada()) {
                System.out.println("BOOM! Game Over.");
                jocAcabat = true;
            } else if (taulell.heGuanyat()) {
                System.out.println("VICTÒRIA!");
                jocAcabat = true;
            }
        
        }
        if (taulell.heGuanyat()) {
            System.out.println("\n--- TOP PUNTUACIONS ---");
            List<String> records = gestorRank.obtenirTopPuntuacions();
            for (String record : records) {
                String[] dades = record.split(",");
                if (dades.length == 2) {
                    System.out.println("Jugador: " + dades[0] + " - Temps: " + dades[1] + "s");
                }
            }
        }
    }
}