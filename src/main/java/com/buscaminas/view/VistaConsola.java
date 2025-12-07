package com.buscaminas.view;

import com.buscaminas.model.Taulell;
import com.buscaminas.model.Casella;
import java.util.Scanner;

public class VistaConsola implements IVista {
	private Taulell taulell;
	private Scanner scanner;
	
	public VistaConsola(Taulell taulell) {
		this.taulell= taulell;
		this.scanner = new Scanner(System.in);
	}
	@Override
    public void mostrarTaulell() {
        System.out.println("\n---------------- Buscaminas ----------------");
        
        System.out.print("   ");
        for (int i = 0; i < taulell.getMida(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < taulell.getMida(); i++) {
            System.out.print(i + " |");

            for (int j = 0; j < taulell.getMida(); j++) {
                Casella c = taulell.getCasella(i, j);
                System.out.print(obtenirSimbolCasella(c, i, j) + "|");
            }
            System.out.println();
        }
    }

    private String obtenirSimbolCasella(Casella c, int x, int y) {
        if (!c.isDestapada()) {
            if (c.isFlag()) {
                return "P";
            }
            return "·";
        }

        if (c.isMina()) {
            return "*";
        } else {
            int minesVeines = taulell.comptarMinesVeines(x, y);
            if (minesVeines == 0) {
                return " ";
            } else {
                return String.valueOf(minesVeines);
            }
        }
    }
    @Override
    public String[] demanarCoordenades() {
        System.out.println("\nIntrodueix coordenades (Fila Columna) separades per espai:");
        System.out.println("Escriu 'F Fila Columna' per posar bandera (ex: F 0 1)");
        System.out.print("> ");

        try {
            String linia = scanner.nextLine();
            return linia.trim().split("\\s+");
        } catch (Exception e) {
            return new String[]{};
        }
    }
	
    @Override
    public void mostrarGameOver() {
        System.out.println("\n#################################");
        System.out.println("#          GAME OVER            #");
        System.out.println("#################################");
    }

    @Override
    public void mostrarGuanyador() {
        System.out.println("\n*********************************");
        System.out.println("*  FELICITATS! HAS GUANYAT!     *");
        System.out.println("*********************************");
    }
}
