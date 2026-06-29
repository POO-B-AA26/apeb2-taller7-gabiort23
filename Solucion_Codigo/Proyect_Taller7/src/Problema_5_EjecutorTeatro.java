
import java.util.ArrayList;


/**
 *Problema 05: Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:

Se desea gestionar la venta de entradas para un espectáculo en un teatro. El patio de butacas del teatro se divide en varias zonas, cada una identificada por su nombre. Los datos de las zonas son los mostrados en la siguiente tabla:

NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO
Principal	200	25$	17.5$
PalcoB	40	70$	40$
Central	400	20$	14$
Lateral	100	15.5$	10$
Para realizar la compra de una entrada, un espectador debe indicar la zona que desea y presentar al vendedor el documento que justifique que tiene algún tipo de descuento (estudiante, abonado o pensionista). El vendedor sacará la entrada del tipo apropiado y de la zona indicada, en el momento de la compra se asignará a la entrada un identificador (un número entero) que permitirá la identificación de la entrada en todas las operaciones que posteriormente se desee realizar con ella.

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
    private String nombre;
    private int numLocalidades;
    private double precioNormal;
    private double precioAbonado;
    private int entradasVendidas;

    public Zona(String nombre, int numLocalidades, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.numLocalidades = numLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.entradasVendidas = 0;
    }

    public String getNombre() { return nombre; }
    public int getNumLocalidades() { return numLocalidades; }
    public double getPrecioNormal() { return precioNormal; }
    public double getPrecioAbonado() { return precioAbonado; }

    public boolean estaCompleta() { return entradasVendidas >= numLocalidades; }
    public void registrarVenta() { entradasVendidas++; }

    @Override
    public String toString() {
        return "Zona{nombre=" + nombre + ", localidades=" + numLocalidades +
               ", precioNormal=" + precioNormal + ", precioAbonado=" + precioAbonado + "}";
    }
}

class Entrada {
    private static int contadorId = 1;
    private int id;
    private Zona zona;
    private String nombreComprador;
    private double precio;

    public Entrada(Zona zona, String nombreComprador, double precio) {
        this.id = contadorId++;
        this.zona = zona;
        this.nombreComprador = nombreComprador;
        this.precio = precio;
    }

    public int getId() { return id; }
    public Zona getZona() { return zona; }
    public String getNombreComprador() { return nombreComprador; }
    public double getPrecio() { return precio; }

    public String mostrarInfo() {
        return "ID       : " + id + "\n" +
               "Zona     : " + zona.getNombre() + "\n" +
               "Comprador: " + nombreComprador + "\n" +
               "Precio   : $" + String.format("%.2f", precio);
    }

    @Override
    public String toString() {
        return "Entrada{id=" + id + ", zona=" + zona.getNombre() +
               ", comprador=" + nombreComprador + ", precio=" + precio + "}";
    }
}

class EntradaNormal extends Entrada {
    public EntradaNormal(Zona zona, String nombreComprador) {
        super(zona, nombreComprador, zona.getPrecioNormal());
    }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo() + "\n" +
               "Tipo     : Normal";
    }

    @Override
    public String toString() {
        return "EntradaNormal{id=" + getId() + ", zona=" + getZona().getNombre() +
               ", comprador=" + getNombreComprador() + ", precio=" + getPrecio() + "}";
    }
}

class EntradaReducida extends Entrada {
    private String tipoDescuento;

    public EntradaReducida(Zona zona, String nombreComprador, String tipoDescuento) {
        super(zona, nombreComprador, zona.getPrecioNormal() * 0.85);
        this.tipoDescuento = tipoDescuento;
    }

    public String getTipoDescuento() { return tipoDescuento; }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo() + "\n" +
               "Tipo     : Reducida (" + tipoDescuento + ") - 15% descuento";
    }

    @Override
    public String toString() {
        return "EntradaReducida{id=" + getId() + ", zona=" + getZona().getNombre() +
               ", comprador=" + getNombreComprador() + ", descuento=" + tipoDescuento +
               ", precio=" + getPrecio() + "}";
    }
}

class EntradaAbonado extends Entrada {
    public EntradaAbonado(Zona zona, String nombreComprador) {
        super(zona, nombreComprador, zona.getPrecioAbonado());
    }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo()  +
               "Tipo     : Abonado";
    }

    @Override
    public String toString() {
        return "EntradaAbonado{id=" + getId() + ", zona=" + getZona().getNombre() +
               ", comprador=" + getNombreComprador() + ", precio=" + getPrecio() + "}";
    }
}

public class Problema_5_EjecutorTeatro {
    public static void main(String[] args) {

        Zona[] zonas = {
            new Zona("Principal", 200, 25.0, 17.5),
            new Zona("PalcoB",    40,  70.0, 40.0),
            new Zona("Central",   400, 20.0, 14.0),
            new Zona("Lateral",   100, 15.5, 10.0)
        };

        ArrayList<Entrada> entradas = new ArrayList<>();


        EntradaNormal e1 = new EntradaNormal(zonas[0], "Carlos Perez");
        zonas[0].registrarVenta();
        entradas.add(e1);
        System.out.println("Entrada vendida:\n" + e1.mostrarInfo());

        System.out.println();
        EntradaAbonado e2 = new EntradaAbonado(zonas[1], "Maria Lopez");
        zonas[1].registrarVenta();
        entradas.add(e2);
        System.out.println("Entrada vendida:" + e2.mostrarInfo());

        System.out.println();
        EntradaReducida e3 = new EntradaReducida(zonas[2], "Andres Ruiz", "estudiante");
        zonas[2].registrarVenta();
        entradas.add(e3);
        System.out.println("Entrada vendida:" + e3.mostrarInfo());

        System.out.println();
        EntradaReducida e4 = new EntradaReducida(zonas[3], "Rosa Mora", "pensionista");
        zonas[3].registrarVenta();
        entradas.add(e4);
        System.out.println("Entrada vendida:\n" + e4.mostrarInfo());

        int buscarId = e1.getId();
        for (int i = 0; i < entradas.size(); i++) {
            if (entradas.get(i).getId() == buscarId) {
                System.out.println("Encontrada:" + entradas.get(i).mostrarInfo());
            }
        }

        for (int i = 0; i < entradas.size(); i++) {
            System.out.println(entradas.get(i));
        }
    }
}
/**Entrada vendida:
ID       : 1
Zona     : Principal
Comprador: Carlos Perez
Precio   : $25.00
Tipo     : Normal

Entrada vendida:ID       : 2
Zona     : PalcoB
Comprador: Maria Lopez
Precio   : $40.00Tipo     : Abonado

Entrada vendida:ID       : 3
Zona     : Central
Comprador: Andres Ruiz
Precio   : $17.00
Tipo     : Reducida (estudiante) - 15% descuento

Entrada vendida:
ID       : 4
Zona     : Lateral
Comprador: Rosa Mora
Precio   : $13.17
Tipo     : Reducida (pensionista) - 15% descuento
Encontrada:ID       : 1
Zona     : Principal
Comprador: Carlos Perez
Precio   : $25.00
Tipo     : Normal
EntradaNormal{id=1, zona=Principal, comprador=Carlos Perez, precio=25.0}
EntradaAbonado{id=2, zona=PalcoB, comprador=Maria Lopez, precio=40.0}
EntradaReducida{id=3, zona=Central, comprador=Andres Ruiz, descuento=estudiante, precio=17.0}
EntradaReducida{id=4, zona=Lateral, comprador=Rosa Mora, descuento=pensionista, precio=13.174999999999999}
 * 
 */