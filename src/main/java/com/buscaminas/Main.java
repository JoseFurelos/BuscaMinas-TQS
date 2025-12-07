package com.buscaminas;

import java.util.List;

import com.buscaminas.controller.JocController;
import com.buscaminas.model.*;
import com.buscaminas.view.VistaConsola;
import java.util.Scanner;

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
        
        long tempsInici = System.currentTimeMillis();
        
        while (!jocAcabat) {
            String[] inputs = vista.demanarCoordenades();

            if (inputs.length < 2) {
                System.out.println("Input invàlid. Has de posar com a mínim dos números.");
                continue;
            }

            int fila, col;
            boolean esBandera = false;

            try {
                if (inputs[0].equalsIgnoreCase("F")) {
                    if (inputs.length < 3) {
                        System.out.println("Per posar bandera falten coordenades (ex: F 0 1)");
                        continue;
                    }
                    esBandera = true;
                    fila = Integer.parseInt(inputs[1]);
                    col = Integer.parseInt(inputs[2]);
                } 
                else {
                    fila = Integer.parseInt(inputs[0]);
                    col = Integer.parseInt(inputs[1]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Les coordenades han de ser números.");
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
                long tempsFi = System.currentTimeMillis();
                long duradaMs = tempsFi - tempsInici;
                int segonsTotals = (int) (duradaMs / 1000);
                System.out.println("Has trigat: " + segonsTotals + " segons.");
                System.out.print("Introdueix el teu nom per al rànquing: ");
                Scanner scannerNom = new Scanner(System.in);
                String nomJugador = scannerNom.nextLine();
                controller.partidaGuanyada(nomJugador, segonsTotals);
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