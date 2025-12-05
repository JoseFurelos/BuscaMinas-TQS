package com.buscaminas.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MockGestorRank implements IGestorRank {
    public boolean sHaCridatGuardar = false;
    public int ultimTempsGuardat = -1;

    @Override
    public void guardarPuntuacio(String nomJugador, int temps) {
        this.sHaCridatGuardar = true;
        this.ultimTempsGuardat = temps;
    }

    @Override
    public List<String> obtenirTopPuntuacions() {
        return null;
    }
}
