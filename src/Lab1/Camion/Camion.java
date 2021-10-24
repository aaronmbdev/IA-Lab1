package Lab1.Camion;

import Lab1.Utils;
import Lab1.enums.EstadoCisterna;

public class Camion {
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
    public void llenarGasolinera(/*final int dias, final int depositos,*/ final int x, final int y) {
        int distancia = Utils.computeDistance(coordX,coordY,x,y);
        int distanciaRetorno = Utils.computeDistance(x,y,coordX,coordY);
        if(puedoHacerViaje(x, y)) {
//            desplazar(x,y, distancia);
            repostarGasolinera(/*depositos*/);
//            updateBalance(dias, depositos);
            desplazar(coordBaseX,coordBaseY /*,distanciaRetorno*/);
        } else {

            System.out.println("No se puede hacer el viaje, km o viajes excedidos.");
            System.out.println("KM:" + kmDisponibles + " Viajes:" + viajes);
        }

    }



    public boolean mePuedoMover() {
        return  ((kmDisponibles > 0) && (viajes >= 1));
    }

    public boolean puedoHacerViaje(final int x, final int y) {
//        int distancia = Utils.computeDistance(coordX,coordY,x,y);
//        return puedoHacerViaje(distancia);

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


/*    private void updateBalance(final int dias, final int depositos) {
        double charge = GAS_PRICE * depositos;
        int porcentaje = 102;
        if(dias != 0) {
            int factor = (int) Math.pow(2,dias);
            porcentaje = 100 - factor;
        }
        charge = (charge * porcentaje) / 100;
        this.balance = this.balance + charge;
    }*/

    private void repostarGasolinera(/*final int depositos*/) {
/*        if(estado != EstadoCisterna.VACIO) {
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
        System.out.println("Error: Se ha intentado repostar con el depósito vacío");*/

        if(this.estado == EstadoCisterna.LLENO) this.estado = EstadoCisterna.MEDIO_LLENO;
        else if(this.estado == EstadoCisterna.MEDIO_LLENO) this.estado = EstadoCisterna.VACIO;
        else /*estado == EstadoCisterna.VACIO*/ System.out.println("Error: Se ha intentado repostar con el depósito vacío");
    }

    private int calcularGasto(final int distancia) {
        return 2*distancia;
    }

    private void desplazar(final int destX, final int destY /*, final int distancia*/) {

//        this.balance = this.balance - calcularGasto(distancia);
//        this.kmDisponibles = this.kmDisponibles - distancia;

        //De su ubicación actual (la base) a su gasolinera destino

        if(estado == EstadoCisterna.MEDIO_LLENO){
            this.coordX = destX;
            this.coordY = destY;
            kmDisponibles -= Utils.computeDistance(coordX, coordY, destX, destY);
        }

        //de su ubicacion actual hasta la gasolinera y luego hacia la base donde se recarga la sisterna
        else if (estado == EstadoCisterna.VACIO){
            kmDisponibles -= Utils.computeDistance(coordX, coordY, destX, destY);
            kmDisponibles -= Utils.computeDistance(destX, destY, coordBaseX, coordBaseY);

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

        //nota
        c.estado = this.estado;

        c.viajes = this.viajes;
        c.kmDisponibles = this.kmDisponibles;
        c.GAS_PRICE = this.GAS_PRICE;

        /*
        System.out.println(
                c.coordX + " " +
                c.coordY + " " +
                c.coordBaseX + " " +
                c.coordBaseY + " " +
                c.estado + " " +
                c.kmDisponibles + " " +
                c.viajes + " ");*/

        return c;
//            this.estado = EstadoCisterna.LLENO;


    }
}
