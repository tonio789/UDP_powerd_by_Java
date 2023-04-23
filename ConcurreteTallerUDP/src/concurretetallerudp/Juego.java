package concurretetallerudp;

public class Juego {

    String palabra = "palabra";
    char[] ArregloChar = new char[palabra.length()];
    char[] ArregloCharGuion = new char[palabra.length()];

    public void llenarArreglo() {
        for (int i = 0; i < palabra.length(); i++) {
            ArregloChar[i] = palabra.charAt(i);
            ArregloCharGuion[i] = '_';
        }
    }

}

