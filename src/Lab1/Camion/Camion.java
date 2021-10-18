package Lab1.Camion;

import Lab1.Utils;
import Lab1.enums.EstadoCisterna;

public class Camion {
    private double balance;
    private int coordX,coordY;
    private EstadoCisterna estado;
    private int coordBaseX,coordBaseY, viajes, kmDisponibles;
    private static double GAS_PRICE = 1000.00;

    public Camion(final int originX, final int originY) {
        this.coordBaseX = originX;
        this.coordX = originX;
        this.coordBaseY = originY;
        this.coordY = originY;
        this.estado = EstadoCisterna.LLENO;
        this.balance = 0;
        this.viajes = 5;
        this.kmDisponibles = 640;
    }

    /**
     * Método que descarga la gasolina en gasolinera.
     * @param depositos Cantidad de depósitos a llenar, debe ser entre 1 y 2.
     */
    public void llenarGasolinera(final int dias, final int depositos, final int x, final int y) {
        int distancia = Utils.computeDistance(coordX,coordY,x,y);
        int distanciaRetorno = Utils.computeDistance(x,y,coordX,coordY);
        if(puedoHacerViaje(distancia)) {
            desplazar(x,y,distancia);
            repostarGasolinera(depositos);
            updateBalance(dias,depositos);
            desplazar(coordBaseX,coordBaseY,distanciaRetorno);
        } else {
            System.out.println("No se puede hacer el viaje, km o viajes excedidos.");
        }
    }

    public boolean puedoHacerViaje(final int distancia) {
        return this.kmDisponibles - (distancia*2) >= 0 && viajes > 1;
    }

    public boolean mePuedoMover() {
        return  this.kmDisponibles > 0 && viajes > 1;
    }

    public boolean puedoHacerViaje(final int x, final int y) {
        int distancia = Utils.computeDistance(coordX,coordY,x,y);
        return puedoHacerViaje(distancia);
    }
    
    public int getKmDisponibles() {
        return kmDisponibles;
    }

    public int getKmDisponibles() {
        return kmDisponibles;
    }

    private void updateBalance(final int dias, final int depositos) {
        double charge = GAS_PRICE * depositos;
        int porcentaje = 102;
        if(dias != 0) {
            int factor = (int) Math.pow(2,dias);
            porcentaje = 100 - factor;
        }
        charge = (charge * porcentaje) / 100;
        this.balance = this.balance + charge;
    }

    private void repostarGasolinera(final int depositos) {
        if(estado != EstadoCisterna.VACIO) {
            if(estado == EstadoCisterna.LLENO) {
                if(depositos == 2) {
                    estado = EstadoCisterna.VACIO;
                } else {
                    estado = EstadoCisterna.MEDIO_LLENO;
                }
            } else {
                if(depositos == 1) {
                    estado = EstadoCisterna.VACIO;
                } else {
                    System.out.println("Error: Se ha intentado repostar más de un depósito, cisterna sin capacidad.");
                }
            }
        }
        System.out.println("Error: Se ha intentado repostar con el depósito vacío");
    }

    private int calcularGasto(final int distancia) {
        return 2*distancia;
    }

    private void desplazar(final int destX, final int destY, final int distancia) {
        this.coordX = destX;
        this.coordY = destY;
        this.balance = this.balance - calcularGasto(distancia);
        this.kmDisponibles = this.kmDisponibles - distancia;
        this.viajes--;
    }
}
