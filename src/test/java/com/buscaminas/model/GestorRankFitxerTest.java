package com.buscaminas.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

class GestorRankFitxerTest {

    private GestorRankFitxer gestor;
    private final String FITXER_TEST = "test_ranking.txt";

    @BeforeEach
    void setUp() {
        gestor = new GestorRankFitxer(FITXER_TEST);
    }

    @AfterEach
    void tearDown() {
        // Esborrem el fitxer de prova després de cada test perquè no quedi brossa al projecte.
        File file = new File(FITXER_TEST);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testConstructorPerDefecte() {
        GestorRankFitxer gestorReal = new GestorRankFitxer();
        gestorReal.guardarPuntuacio("TestDefault", 100);
        File fitxerReal = new File("ranking.txt");
        assertTrue(fitxerReal.exists(), "El constructor per defecte hauria de crear 'ranking.txt'");

        //esborrem el fitxer real
        if (fitxerReal.exists()) {
            fitxerReal.delete();
        }
    }
    @Test
    void testGuardarILlegirPuntuacio() {
        // Guardem una puntuació de prova
        gestor.guardarPuntuacio("TestPlayer", 999);

        List<String> records = gestor.obtenirTopPuntuacions();

        assertNotNull(records, "La llista no hauria de ser null");
        assertFalse(records.isEmpty(), "La llista no hauria d'estar buida");
        assertEquals(1, records.size(), "Hauria d'haver-hi 1 registre");
        assertEquals("TestPlayer,999", records.get(0));
    }

    @Test
    void testAfegirMultiplesPuntuacions() {
        
        gestor.guardarPuntuacio("Jugador1", 10);
        gestor.guardarPuntuacio("Jugador2", 20);

        List<String> records = gestor.obtenirTopPuntuacions();

        assertEquals(2, records.size());
        assertEquals("Jugador1,10", records.get(0));
        assertEquals("Jugador2,20", records.get(1));
    }
    
    @Test
    void testLlegirFitxerInexistent() {

        List<String> records = gestor.obtenirTopPuntuacions();
        
        assertNotNull(records);
        assertTrue(records.isEmpty(), "Si no hi ha fitxer, la llista ha de ser buida");
    }
    
    @Test
    void testErrorGuardantPuntuacio() {
        GestorRankFitxer gestorFallit = new GestorRankFitxer("src"); 

        assertDoesNotThrow(() -> {
            gestorFallit.guardarPuntuacio("Jugador", 100);
        });
        
    }

    @Test
    void testErrorLlegintRanquing() {
        GestorRankFitxer gestorFallit = new GestorRankFitxer("src"); 

        List<String> resultat = gestorFallit.obtenirTopPuntuacions();

        assertNotNull(resultat);
        assertTrue(resultat.isEmpty());
    }
}