package com.buscaminas.model;

public class Taulell {
    private Casella[][] caselles;
    private int mida;

    public Taulell(int mida) {
        this.mida = mida;
        this.caselles = new Casella[mida][mida];
        inicialitzarCaselles();
    }
    
    private void inicialitzarCaselles() {
        
    }

    public int getMida() {
        return mida;
    }
    
    public Casella getCasella(int x, int y) {
        
        return caselles[x][y];
    }
}