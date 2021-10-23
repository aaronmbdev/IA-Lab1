package Lab1.Peticiones;

import java.lang.Math;

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

    public boolean isCumplido() {
        return cumplido;
    }

    public int getDiasPendiente() {
        return diasPendiente;
    }
    public double getGananciasActuales(){
        if (diasPendiente == 0) return 1000;
        else{
            //%𝑝𝑟𝑒𝑐𝑖𝑜 = (100 − 2^𝑑𝑖𝑎𝑠) %
            return 1000 * ((100 - Math.pow(2, diasPendiente) ) / 100);
        }
    }


}
