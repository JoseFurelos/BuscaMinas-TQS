package com.buscaminas.model;

import java.util.List;

public interface IGestorRank {
	void guardarPuntuacio(String nomJugador, int temps);
    List<String> obtenirTopPuntuacions();
}
