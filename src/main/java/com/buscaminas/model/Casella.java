package com.buscaminas.model;

public class Casella {
    private boolean destapada;
    private boolean isMina;
    
    public Casella() {
        this.destapada = false;
    }

    public boolean isDestapada() {
        return destapada;
    }
    
    public boolean isMina() {
    	return isMina;
    }
    public void setMina(boolean mina){
    	this.isMina = mina;
    }
    public void destapar() {
        this.destapada = true;
    }
}