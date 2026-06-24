/**
 * Problema 04: Sistema de nómina para trabajadores de una empresa.
 * Revisado y mejorado con apoyo de IA (Claude).
 * Mejoras: encapsulamiento, formato legible en imprimirNomina(), 
 * eliminada dependencia circular Trabajador-Jefe.
 * @author Gabriela Ortega
 * @version 1.0
 */
class Trabajador {
    private String nombre;
    private String apellidos;
    private String direccion;
    private String dni;
    private Trabajador jefe;

    public Trabajador(String nombre, String apellidos, String direccion, String dni, Trabajador jefe) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.jefe = jefe;
    }

    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getDireccion() { return direccion; }
    public String getDni() { return dni; }
    public Trabajador getJefe() { return jefe; }

    public double calcularSalario() { return 0.0; }

    public String imprimirNomina() {
        return "=== NOMINA ===\n" +
               "Nombre   : " + nombre + " " + apellidos + "\n" +
               "DNI      : " + dni + "\n" +
               "Direccion: " + direccion + "\n" +
               "Jefe     : " + (jefe != null ? jefe.getNombre() + " " + jefe.getApellidos() : "Sin jefe") + "\n" +
               "Salario  : " + calcularSalario();
    }

    @Override
    public String toString() {
        return "Trabajador{nombre=" + nombre + ", apellidos=" + apellidos +
               ", dni=" + dni + ", jefe=" + (jefe != null ? jefe.getNombre() : "Ninguno") + "}";
    }
}

class Jefe extends Trabajador {
    private double sueldoFijo;

    public Jefe(String nombre, String apellidos, String direccion, String dni, double sueldoFijo) {
        super(nombre, apellidos, direccion, dni, null);
        this.sueldoFijo = sueldoFijo;
    }

    public double getSueldoFijo() { return sueldoFijo; }

    @Override
    public double calcularSalario() { return sueldoFijo; }

    @Override
    public String imprimirNomina() {
        return "=== NOMINA (JEFE) ===\n" +
               "Nombre    : " + getNombre() + " " + getApellidos() + "\n" +
               "DNI       : " + getDni() + "\n" +
               "Direccion : " + getDireccion() + "\n" +
               "Sueldo Fijo: " + sueldoFijo;
    }

    @Override
    public String toString() {
        return "Jefe{nombre=" + getNombre() + ", apellidos=" + getApellidos() +
               ", dni=" + getDni() + ", sueldoFijo=" + sueldoFijo + "}";
    }
}

class FijoMensual extends Trabajador {
    private double cantidadFija;

    public FijoMensual(String nombre, String apellidos, String direccion, String dni, Trabajador jefe, double cantidadFija) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.cantidadFija = cantidadFija;
    }

    public double getCantidadFija() { return cantidadFija; }

    @Override
    public double calcularSalario() { return cantidadFija; }

    @Override
    public String imprimirNomina() {
        return super.imprimirNomina() + "\n" +
               "Tipo      : Fijo Mensual\n" +
               "Cantidad  : " + cantidadFija;
    }

    @Override
    public String toString() {
        return "FijoMensual{nombre=" + getNombre() + ", dni=" + getDni() +
               ", cantidadFija=" + cantidadFija + ", jefe=" + (getJefe() != null ? getJefe().getNombre() : "Ninguno") + "}";
    }
}

class Comisionista extends Trabajador {
    private double ventas;
    private double porcentajeComision;

    public Comisionista(String nombre, String apellidos, String direccion, String dni, Trabajador jefe, double porcentajeComision) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.porcentajeComision = porcentajeComision;
        this.ventas = 0.0;
    }

    public void fijarVentas(double ventas) { this.ventas = ventas; }
    public double getVentas() { return ventas; }
    public double getPorcentajeComision() { return porcentajeComision; }

    @Override
    public double calcularSalario() { return ventas * porcentajeComision / 100; }

    @Override
    public String imprimirNomina() {
        return super.imprimirNomina() + "\n" +
               "Tipo      : Comisionista\n" +
               "Ventas    : " + ventas + "\n" +
               "Comision  : " + porcentajeComision + "%\n" +
               "Total     : " + calcularSalario();
    }

    @Override
    public String toString() {
        return "Comisionista{nombre=" + getNombre() + ", dni=" + getDni() +
               ", ventas=" + ventas + ", comision=" + porcentajeComision +
               ", jefe=" + (getJefe() != null ? getJefe().getNombre() : "Ninguno") + "}";
    }
}

class PorHoras extends Trabajador {
    private int horasTrabajadas;
    private double precioPrimeras40;
    private double precioHorasExtra;

    public PorHoras(String nombre, String apellidos, String direccion, String dni, Trabajador jefe, double precioPrimeras40, double precioHorasExtra) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.precioPrimeras40 = precioPrimeras40;
        this.precioHorasExtra = precioHorasExtra;
        this.horasTrabajadas = 0;
    }

    public void fijarHoras(int horas) { this.horasTrabajadas = horas; }
    public int getHorasTrabajadas() { return horasTrabajadas; }
    public double getPrecioPrimeras40() { return precioPrimeras40; }
    public double getPrecioHorasExtra() { return precioHorasExtra; }

    @Override
    public double calcularSalario() {
        if (horasTrabajadas <= 40)
            return horasTrabajadas * precioPrimeras40;
        else
            return (40 * precioPrimeras40) + ((horasTrabajadas - 40) * precioHorasExtra);
    }

    @Override
    public String imprimirNomina() {
        return super.imprimirNomina() + "\n" +
               "Tipo           : Por Horas\n" +
               "Horas          : " + horasTrabajadas + "\n" +
               "Precio/h(<=40) : " + precioPrimeras40 + "\n" +
               "Precio/h(>40)  : " + precioHorasExtra + "\n" +
               "Total          : " + calcularSalario();
    }

    @Override
    public String toString() {
        return "PorHoras{nombre=" + getNombre() + ", dni=" + getDni() +
               ", horas=" + horasTrabajadas + ", precio40=" + precioPrimeras40 +
               ", precioExtra=" + precioHorasExtra +
               ", jefe=" + (getJefe() != null ? getJefe().getNombre() : "Ninguno") + "}";
    }
}

public class Problema_4_EjecutorNomina {
    public static void main(String[] args) {
        Jefe jefe1 = new Jefe("Xavier", "Ortega", "Av. Orillas de Zamora", "1712345678", 2500.00);
        FijoMensual emp1 = new FijoMensual("Denissen", "Sinche", "Calle 10 de agosto", "1798765432", jefe1, 800.00);
        Comisionista emp2 = new Comisionista("Juan", "Herrera", "Calle 24 de Mayo", "1756781234", jefe1, 15.0);
        emp2.fijarVentas(5000.00);
        PorHoras emp3 = new PorHoras("Ximena", "Hurtado", "Calle Paris", "1745678901", jefe1, 5.00, 8.00);
        emp3.fijarHoras(50);

        System.out.println(jefe1.imprimirNomina());
        System.out.println();
        System.out.println(emp1.imprimirNomina());
        System.out.println();
        System.out.println(emp2.imprimirNomina());
        System.out.println();
        System.out.println(emp3.imprimirNomina());
        System.out.println();
        System.out.println(jefe1);
        System.out.println(emp1);
        System.out.println(emp2);
        System.out.println(emp3);
    }
}

/**
=== NOMINA (JEFE) ===
Nombre    : Xavier Ortega
DNI       : 1712345678
Direccion : Av. Orillas de Zamora
Sueldo Fijo: 2500.0

=== NOMINA ===
Nombre   : Denissen Sinche
DNI      : 1798765432
Direccion: Calle 10 de agosto
Jefe     : Xavier Ortega
Salario  : 800.0
Tipo      : Fijo Mensual
Cantidad  : 800.0

=== NOMINA ===
Nombre   : Juan Herrera
DNI      : 1756781234
Direccion: Calle 24 de Mayo
Jefe     : Xavier Ortega
Salario  : 750.0
Tipo      : Comisionista
Ventas    : 5000.0
Comision  : 15.0%
Total     : 750.0

=== NOMINA ===
Nombre   : Ximena Hurtado
DNI      : 1745678901
Direccion: Calle Paris
Jefe     : Xavier Ortega
Salario  : 280.0
Tipo           : Por Horas
Horas          : 50
Precio/h(<=40) : 5.0
Precio/h(>40)  : 8.0
Total          : 280.0

Jefe{nombre=Xavier, apellidos=Ortega, dni=1712345678, sueldoFijo=2500.0}
FijoMensual{nombre=Denissen, dni=1798765432, cantidadFija=800.0, jefe=Xavier}
Comisionista{nombre=Juan, dni=1756781234, ventas=5000.0, comision=15.0, jefe=Xavier}
PorHoras{nombre=Ximena, dni=1745678901, horas=50, precio40=5.0, precioExtra=8.0, jefe=Xavier}
 */