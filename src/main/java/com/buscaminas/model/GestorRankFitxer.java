package com.buscaminas.model;

import java.io.*;
import java.util.*;

public class GestorRankFitxer implements IGestorRank {

	private String nomFitxer;

    public GestorRankFitxer() {
        this.nomFitxer = "ranking.txt";
    }
    
    public GestorRankFitxer(String nomFitxerTest) {
        this.nomFitxer = nomFitxerTest;
    }
    
    @Override
    public void guardarPuntuacio(String nomJugador, int temps) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.nomFitxer, true))) {
            writer.write(nomJugador + "," + temps);
            writer.newLine();
            System.out.println("Puntuació guardada correctament a " + this.nomFitxer);
        } catch (IOException e) {
            System.err.println("Error guardant la puntuació: " + e.getMessage());
        }
    }

    @Override
    public List<String> obtenirTopPuntuacions() {
        List<String> puntuacions = new ArrayList<>();
        File fitxer = new File(this.nomFitxer);

        if (!fitxer.exists()) {
            return puntuacions; 
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fitxer))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                puntuacions.add(linia);
            }   
            
        } catch (IOException e) {
            System.err.println("Error llegint el rànquing: " + e.getMessage());
        }
        return puntuacions;
    }
}