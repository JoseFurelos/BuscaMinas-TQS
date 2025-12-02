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
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                caselles[i][j] = new Casella();
            }
        }
    }

    public int getMida() {
        return mida;
    }
    public Casella getCasella(int x, int y) {
        if (x < 0 || x >= mida || y < 0 || y >= mida) {
            throw new IndexOutOfBoundsException("Coordenades fora del taulell");
        }
        return caselles[x][y];
    }
}