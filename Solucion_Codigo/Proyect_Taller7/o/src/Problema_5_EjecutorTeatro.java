
/**
 *Problema 05:Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:

Se desea gestionar la venta de entradas para un espectáculo en un teatro. El patio de butacas del teatro se divide en varias zonas,
* cada una identificada por su nombre. Los datos de las zonas son los mostrados en la siguiente tabla:

NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMAL	PRECIO ABONADO
Principal	         200	             25$	    17.5$
PalcoB	                 40	             70$	    40$
Central	                 400	             20$	    14$
Lateral	                 100	             15.5$	    10$
Para realizar la compra de una entrada, un espectador debe indicar la zona que desea y presentar al vendedor el 
* documento que justifique que tiene algún tipo de descuento (estudiante, abonado o pensionista). El vendedor sacará la entrada del 
* tipo apropiado y de la zona indicada, en el momento de la compra se asignará a la entrada un identificador (un número entero) que
* permitirá la identificación de la entrada en todas las operaciones que posteriormente se desee realizar con ella.

Una entrada tiene como datos asociados su identificador, la zona a la que pertenece y el nombre del comprador.

Los precios de las entradas dependen de la zona y del tipo de entrada según lo explicado a continuación:

Entradas normales: su precio es el precio normal de la zona elegida sin ningún tipo de descuento.
Entradas reducidas (para estudiantes o pensionistas): su precio tiene una rebaja del 15% sobre el precio normal de la zona elegida.
Entradas abonado: su precio es el precio para abonados de la zona elegida.
La interacción entre el vendedor y la aplicación es la descrita en los siguientes casos de usos. 
 * @author Gabriela Ortega
 * @version 1.0
 */
class Zona {
    public String nombreZona; 
    public int cantidadLocalidades;
    public double precioNormal, precioAbonado;

    public Zona(String nombreZona, int cantidadLocalidades, double precioNormal, double precioAbonado) {
        this.nombreZona = nombreZona;
        this.cantidadLocalidades = cantidadLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
    }

    @Override
    public String toString() {
        return "Zona: " + nombreZona + " | Precio Normal: " + precioNormal + " | Precio Abonado: $" + precioAbonado;
    }
}

class Entrada {
    public int idEnt, cantidadEnt;
    public Zona zona;
    public String nombreComprador;
    public double costoEntrada;

    public Entrada(int idEnt, int cantidadEnt, Zona zona, String nombreComprador) {
        this.idEnt = idEnt;
        this.cantidadEnt = cantidadEnt;
        this.zona = zona;
        this.nombreComprador = nombreComprador;
    }

    public double calcularCostoEntrada(double precioEntrada) {
        this.costoEntrada = this.cantidadEnt * precioEntrada;
        return this.costoEntrada;
    }

    @Override
    public String toString() {
        return "ID: " + idEnt + " | Comprador: " + nombreComprador + " | Cantidad: " + cantidadEnt + " | Costo Total: $" + costoEntrada + " (" + zona.nombreZona + ")";
    }
}

class Entrada_Normal extends Entrada {
    
    public Entrada_Normal(int idEnt, int cantidadEnt, Zona zona, String nombreComprador) {
        super(idEnt, cantidadEnt, zona, nombreComprador);
    }

    public double calcularCostoEntrada() {
        this.costoEntrada = super.calcularCostoEntrada(this.zona.precioNormal);
        return this.costoEntrada;
    }
}

class Entrada_Reducida extends Entrada {
    public double porcentajeRebaja;

    public Entrada_Reducida(int idEnt, int cantidadEnt, Zona zona, String nombreComprador) {
        super(idEnt, cantidadEnt, zona, nombreComprador);
        this.porcentajeRebaja = porcentajeRebaja;
    }

    public double calcularCostoEntrada() {
        double precioConDescuento = this.zona.precioNormal - (this.zona.precioNormal * (this.porcentajeRebaja / 100));
        this.costoEntrada = super.calcularCostoEntrada(precioConDescuento);
        return this.costoEntrada;
    }

    @Override
    public String toString() {
        return super.toString() + " [Descuento aplicado: " + porcentajeRebaja + "%]";
    }
}

class Entrada_Abonada extends Entrada {

    public Entrada_Abonada(int idEnt, int cantidadEnt, Zona zona, String nombreComprador) {
        super(idEnt, cantidadEnt, zona, nombreComprador);
    }

    public double calcularCostoEntrada() {
        this.costoEntrada = super.calcularCostoEntrada(this.zona.precioAbonado);
        return this.costoEntrada;
    }
}

public class Problema_5_EjecutorTeatro {
    public static void main(String[] args) {
        Zona zona1 = new Zona("Principal", 200, 25, 17.5);
        Zona zona2 = new Zona("PalcoB", 40, 70, 40);
        Zona zona3 = new Zona("Central", 400, 20, 14);
        Zona zona4 = new Zona("Lateral", 100, 15.5, 10);
        
        Entrada_Normal entNormall = new Entrada_Normal(1, 2, zona4, "Daniel");
        Entrada_Reducida entReducida1 = new Entrada_Reducida(15, 2, zona1, "Gaby");
        Entrada_Abonada entAbonada1 = new Entrada_Abonada(3, 1, zona4, "Allyson");
        
        entNormall.calcularCostoEntrada();
        entReducida1.calcularCostoEntrada();
        entAbonada1.calcularCostoEntrada();
        
        System.out.println(entNormall);
        System.out.println(entReducida1);
        System.out.println(entAbonada1);
        
    }
}