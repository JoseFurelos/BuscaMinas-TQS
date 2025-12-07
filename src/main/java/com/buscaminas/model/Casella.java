package com.buscaminas.model;

public class Casella {
    private boolean destapada;
    private boolean isMina;
    private boolean isFlag;
    
    public Casella() {
        this.destapada = false;
    }

    public boolean isDestapada() {
        return destapada;
    }
    public boolean isFlag(){
    	return isFlag;
    }
    
    public boolean isMina() {
    	return isMina;
    }
    public void setMina(boolean mina){
    	this.isMina = mina;
    }
    public void setFlag(boolean flag){
    	this.isFlag = true;
    }
    public void destapar() {
        this.destapada = true;
    }
}