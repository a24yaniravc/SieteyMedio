package sieteymedia;

import recursos.Baraja;
import recursos.Carta;

public class SieteYMedia {
    Baraja baraja;
    Carta[] cartasJugador;
    Carta[] cartasBanca;

    public SieteYMedia() {
        baraja = new Baraja();
        baraja.barajar();
        cartasJugador = new Carta[15];
        cartasBanca = new Carta[15];
    }

    // Método para obtener las cartas del jugador
    public Carta[] getCartasJugador() {
        return cartasJugador;
    }

    // Método para obtener las cartas de la banca
    public Carta[] getCartasBanca() {
        return cartasBanca;
    }

    // Función que devuelve el turno del jugador, pero sin interactuar con el usuario
    public boolean turnoJugador() {
        char opc = 'C';
        // El jugador siempre recibe al menos una carta
        while (valorCartas(cartasJugador) < 7.5 && opc == 'C') {
            Carta c = baraja.darCartas(1)[0];
            insertarCartaEnArray(cartasJugador, c);

            // Aquí solo devolvemos el valor de las cartas del jugador, la interacción la hace InterfaceConsola
            opc = 'C';  // Para simular que siempre puede decidir pedir o plantarse (la decisión la toma InterfaceConsola)
        }
        return valorCartas(cartasJugador) <= 7.5;
    }

    // Función que devuelve el turno de la banca
    public boolean turnoBanca() {
        double valorCartasJugador = valorCartas(cartasJugador);
        if (valorCartasJugador > 7.5) {
            return false; // El jugador se ha pasado, la banca gana
        }

        // La banca juega hasta empatar o superar
        while (valorCartas(cartasBanca) < valorCartasJugador) {
            Carta c = baraja.darCartas(1)[0];
            insertarCartaEnArray(cartasBanca, c);
        }

        return valorCartas(cartasBanca) <= 7.5;
    }

    // Método para obtener el valor total de las cartas de un jugador
    double valorCartas(Carta[] cartas) {
        double total = 0.0;
        int i = 0;
        while (cartas[i] != null) {
            int val = cartas[i].getNumero();
            total += (val > 7) ? 0.5 : val;
            i++;
        }
        return total;
    }

    // Método para insertar una carta en el array del jugador
    private void insertarCartaEnArray(Carta[] cartas, Carta c) {
        int i = 0;
        while (cartas[i] != null) {
            i++;
        }
        cartas[i] = c;
    }

    // Método que devuelve el estado de las cartas de un jugador
    public String mostrarCartas(Carta[] cartas) {
        StringBuilder resultado = new StringBuilder();
        int i = 0;
        while (cartas[i] != null) {
            resultado.append("\t" + cartas[i]);
            i++;
        }
        return resultado.toString();
    }
}
