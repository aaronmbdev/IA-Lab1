package Lab1.Peticiones;

import java.lang.Math;

public class Peticion {
    private int diasPendiente, coordX, coordY;
    private boolean cumplido;

    private Peticion() {
        this.diasPendiente = -1;
        this.coordX = -1;
        this.coordY = -1;
        this.cumplido = true;

    }

    public Peticion(final int diasPendiente, final int coordX, final int coordY) {
        this.diasPendiente = diasPendiente;
        this.coordX = coordX;
        this.coordY = coordY;
        this.cumplido = false;

    }

    public int getDiasPendiente() {
        return diasPendiente;
    }

    public int getCoordX(){
        return coordX;
    }

    public int getCoordY(){
        return coordY;
    }

    public void setCumplido(boolean cumplido) {
        this.cumplido = cumplido;
    }

    public boolean isCumplido() {
        return cumplido;
    }

    public double getGananciasActuales(){
        if (diasPendiente == 0) return 1000;
        else{
            return 1000 * ((100 - Math.pow(2, diasPendiente) ) / 100);
        }
    }


    public Peticion getCopy(){
        Peticion p = new Peticion();

        p.diasPendiente = this.diasPendiente;
        p.coordX = this.coordX;
        p.coordY = this.coordY;
        p.cumplido = this.cumplido;

        return p;
    }
}
