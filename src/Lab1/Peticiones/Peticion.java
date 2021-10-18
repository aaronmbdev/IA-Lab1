package Lab1.Peticiones;

public class Peticion {
    private int diasPendiente, coordX, coordY;
    private boolean cumplido;

    public Peticion(final int diasPendiente, final int coordX, final int coordY) {
        this.diasPendiente = diasPendiente;
        this.coordX = coordX;
        this.coordY = coordY;
        this.cumplido = false;
    }

    public void setCumplido(boolean cumplido) {
        this.cumplido = cumplido;
    }
}
