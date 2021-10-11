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

    /**
     * Método que ejecuta el desplazamiento del camión de su punto actual a destino
     * @param destX Coordenada X del destino
     * @param destY Coordenada Y del destino
     */
    public void desplazar(final int destX, final int destY) {

    }

    /**
     * Método que retorna el camión a centro de distribución
     */
    public void volverABase() {

    }

    /**
     * Método que llena el tanque del camión en centro de distribución
     */
    public void llenarTanque() {

    }

    /**
     * Método que descarga la gasolina en gasolinera.
     * @param depositos Cantidad de depósitos a llenar, debe ser entre 1 y 2.
     */
    public void llenarGasolinera(final int depositos) {
        
    }
}
