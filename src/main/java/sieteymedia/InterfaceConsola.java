package sieteymedia;

import java.util.Scanner;

public class InterfaceConsola {
    private SieteYMedia juego;
    private Scanner sc;

    public InterfaceConsola() {
        juego = new SieteYMedia();
        sc = new Scanner(System.in);
        presentarJuego();
        jugar();
    }

    public static void main(String[] args) {
        new InterfaceConsola();
    }

    public void presentarJuego() {
        System.out.println("- El usuario es el jugador y el ordenador la  banca.");
        System.out.println("- No hay en la baraja 8s y 9s. El 10 es la sota, el 11 el caballo y el 12 el Rey.");
        System.out.println("- Las figuras (10-sota, 11-caballo y 12-rey) valen medio punto y, el resto, su valor.");
        System.out.println("- Hay dos turnos de juego: el turno del jugador y el turno de la banca. Se comienza por el turno del jugador.");
        System.out.println("- El jugador va pidiendo cartas a la banca de una en una.");
        System.out.println("- El jugador puede plantarse en cualquier momento.");
        System.out.print("- Si la suma de los valores de las cartas sacadas es superior ");
        System.out.println("a 7 y medio, el jugador 'se pasa de siete y medio' y pierde.");
        System.out.println("- Si el jugador no se pasa, comienza a sacar cartas la banca y ésta está obligada a sacar cartas hasta empatar o superar al jugador.");
        System.out.println("- Si la banca consigue empatar o superar la puntuación del jugador 'sin pasarse de siete y medio', gana la banca.");
        System.out.println("- La banca no se puede plantar y tiene que empatar o superar la puntuación del jugador sin pasarse.");
        System.out.println("- En este proceso puede ocurrir que la banca 'se pase' y entonces pierde la banca y gana el jugador.");
        System.out.println("\nEmpecemos!!!\n");
    }

    void jugar() {
        boolean jugadorActivo = true;

        // Empezamos el turno del jugador
        while (jugadorActivo) {
            // Mostrar las cartas del jugador
            System.out.println("Estas son tus cartas: " + juego.mostrarCartas(juego.getCartasJugador()));
            // Pedir al jugador si quiere seguir o plantarse
            System.out.println("¿Quieres [C]oger una carta o [P]lantarte?");
            char decision = sc.next().toUpperCase().charAt(0);

            if (decision == 'C') {
                jugadorActivo = juego.turnoJugador();
                if (jugadorActivo) {
                    // Si el jugador decide seguir, mostramos sus cartas después de cada carta
                    System.out.println("Estas son tus cartas ahora: " + juego.mostrarCartas(juego.getCartasJugador()));
                }
            } else if (decision == 'P') {
                jugadorActivo = false;
            }
        }

        // Una vez el jugador termina, pasamos al turno de la banca
        System.out.println("\nTurno de la banca...");
        boolean bancaActiva = juego.turnoBanca();

        // Verificamos los resultados y mostramos quién ha ganado
        if (!bancaActiva) {
            System.out.println("¡La banca se ha pasado! El jugador gana.");
        } else if (juego.valorCartas(juego.getCartasJugador()) > juego.valorCartas(juego.getCartasBanca())) {
            System.out.println("¡El jugador gana!");
        } else {
            System.out.println("¡La banca gana!");
        }
    }
}
