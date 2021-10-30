package Lab1.Camion;

import Lab1.Utils;
import Lab1.enums.EstadoCisterna;

public class Camion implements Cloneable {
//    private double balance;
    private int coordX,coordY;
    private EstadoCisterna estado;
    private int coordBaseX,coordBaseY, viajes, kmDisponibles;
    private static double GAS_PRICE = 1000.00;

    private Camion() {
        this.coordBaseX = -1;
        this.coordX = -1;
        this.coordBaseY = -1;
        this.coordY = -1;
        this.estado = EstadoCisterna.LLENO;
//        this.balance = 0;
        this.viajes = 5;
        this.kmDisponibles = 640;
    }

    public Camion(final int originX, final int originY) {
        this.coordBaseX = originX;
        this.coordX = originX;
        this.coordBaseY = originY;
        this.coordY = originY;
        this.estado = EstadoCisterna.LLENO;
//        this.balance = 0;
        this.viajes = 5;
        this.kmDisponibles = 640;
    }

    /**
     * Método que descarga la gasolina en gasolinera.
     */
    public void llenarGasolinera(final int x, final int y) {
        repostarGasolinera();
        desplazar(x,y);
    }



    public boolean mePuedoMover() {
        return  ((kmDisponibles > 0) && (viajes >= 1));
    }

    public boolean puedoHacerViaje(final int x, final int y) {
        int distanciaDestino = Utils.computeDistance(coordX,coordY,x,y);

        if (estado == EstadoCisterna.LLENO) {
            return  viajes >= 1 && this.kmDisponibles - (distanciaDestino*2) >= 0 ;
        }
        else if (estado == EstadoCisterna.MEDIO_LLENO){
            return (viajes >= 1 && (this.kmDisponibles - distanciaDestino - Utils.computeDistance(x,y,coordBaseX,coordBaseY) >= 0));
        }
        else{
            System.out.println("ERROR");
            return false;
        }

    }
    
    public int getKmDisponibles() {
        return kmDisponibles;
    }


    private void repostarGasolinera() {
        if(this.estado == EstadoCisterna.LLENO) this.estado = EstadoCisterna.MEDIO_LLENO;
        else if(this.estado == EstadoCisterna.MEDIO_LLENO) this.estado = EstadoCisterna.VACIO;
        else System.out.println("Error: Se ha intentado repostar con el depósito vacío");
    }


    private void desplazar(final int destX, final int destY) {
        if(estado == EstadoCisterna.MEDIO_LLENO){

            kmDisponibles = kmDisponibles - Utils.computeDistance(coordX, coordY, destX, destY);
            this.coordX = destX;
            this.coordY = destY;

        }
        else if (estado == EstadoCisterna.VACIO){
            kmDisponibles = kmDisponibles - Utils.computeDistance(coordX, coordY, destX, destY);
            kmDisponibles = kmDisponibles - Utils.computeDistance(destX, destY, coordBaseX, coordBaseY);

            this.coordX = this.coordBaseX;
            this.coordY = this.coordBaseY;
            this.viajes--;

            this.estado  = EstadoCisterna.LLENO;
        }
        else System.out.println("Camíon LLENO ERROR");
    }

    public EstadoCisterna getEstadoCisterna() {
        return estado;
    }

    public int getViajes() {
        return viajes;
    }

    public Camion getCopy(){
        Camion c = new Camion();
        c.coordBaseX = this.coordBaseX;
        c.coordX = this.coordX;
        c.coordBaseY = this.coordBaseY;
        c.coordY = this.coordY;
        c.estado = this.estado;
        c.viajes = this.viajes;
        c.kmDisponibles = this.kmDisponibles;
        c.GAS_PRICE = GAS_PRICE;
        return c;


    }
}
