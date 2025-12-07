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
        
    }

    @Override
    public List<String> obtenirTopPuntuacions() {
        
    }
}