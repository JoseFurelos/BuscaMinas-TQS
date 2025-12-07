package com.buscaminas.model;

import java.util.Random;

public class GeneradorMines implements IGeneradorMines {
    private Random random = new Random();
    private double probabilitat;

    public GeneradorMines() {
        this.probabilitat = 0.15;
    }

    @Override
    public boolean hiHaMina(int x, int y) {
    	if(x == 0 && y == 0)
    		return false;
        return random.nextDouble() < probabilitat;
    }
}