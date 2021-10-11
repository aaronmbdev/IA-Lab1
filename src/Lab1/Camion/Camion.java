package Lab1.Camion;

import Lab1.enums.EstadoCisterna;

public class Camion {
    private double balance;
    private int coordX,coordY;
    private EstadoCisterna estado;
    private int coordBaseX,coordBaseY;

    public Camion(final int originX, final int originY) {
        this.coordBaseX = originX;
        this.coordX = originX;
        this.coordBaseY = originY;
        this.coordY = originY;
        this.estado = EstadoCisterna.VACIO;
        this.balance = 0;
    }
}
