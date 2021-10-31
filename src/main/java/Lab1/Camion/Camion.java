package Lab1.Camion;

import Lab1.Utils;
import Lab1.enums.EstadoCisterna;

public class Camion  {
    private int coordX,coordY;
    private EstadoCisterna estado = EstadoCisterna.LLENO;
    private int coordBaseX,coordBaseY;
    private int viajes = 5;
    private int kmDisponibles = 640;
    private double beneficioNeto = 0;
    private static double GAS_PRICE = 1000.00;

    public Camion(final int originX, final int originY) {
        this.coordBaseX = originX;
        this.coordX = originX;
        this.coordBaseY = originY;
        this.coordY = originY;
    }

    public Camion() {

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
        return c;
    }

    public double calcularBeneficio() {
        double gasto = calcularGastos();
        return beneficioNeto - gasto;
    }

    public void atenderPeticion(final int x, final int y, final int dias) {
        repostarGasolinera();
        desplazar(x,y);
        beneficioNeto = beneficioNeto + calcularGananciasPeticion(dias);
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

    private double calcularGastos() {
        return (640-kmDisponibles) * 2;
    }

    private double calcularGananciasPeticion(final int dias) {
        double mult = 1.02;
        if(dias != 0) {
            double coef = (int) Math.pow(2,dias);
            double perc = 100 - coef;
            mult = perc / 100;
        }
        return GAS_PRICE * mult;
    }
}
