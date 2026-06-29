import java.util.ArrayList;
/**
 *Problema 06: El banco UN BANCO mantiene las cuentas de varios clientes.
 * Los datos que describen a cada una de las cuentas consisten en el número
 * de cuenta, el nombre del cliente y el balance actual. Escriba una clase 
 * para implementar dicha cuenta bancaria. El método constructor debe aceptar
 * como parámetros el número de cuenta y el nombre. Debe proporcionarse métodos
 * para depositar o retirar una cantidad de dinero y obtener el balance actual.
El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de AHORROS. 
* Una cuenta de cheques puede sobregirarse (el balance puede ser menor que cero), 
* pero una cuenta de ahorros no. Al final de cada mes, se calcula el interés sobre la 
* cantidad que tenga la cuenta de ahorros. Este interés se suma al balance. Escriba 
* clases para describir cada uno de estos tipos de cuentas, haciendo un máximo uso de la
* herencia. La clase de la cuenta de ahorros debe proporcionar un método que sea invocado
* para calcular el interés. Además, el banco está pensando en implementar una cuenta PLATINO
* que viene siendo similar a los otros dos tipos anteriores de cuentas bancarias, ésta tiene
* el interés del 10%, sin cargos ni castigos por sobregiro.
 * @author Desconocido
 */
class CuentaBancaria {
    private String numeroCuenta;
    private String nombreCliente;
    protected double balance;

    public CuentaBancaria(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance = 0.0;
    }

    public String getNumeroCuenta() { return numeroCuenta; }
    public String getNombreCliente() { return nombreCliente; }
    public double getBalance() { return balance; }

    public void depositar(double cantidad) {
        if (cantidad > 0) balance += cantidad;
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0) {
            balance -= cantidad;
            return true;
        }
        return false;
    }

    public String mostrarEstado() {
        return "Cuenta   : " + numeroCuenta + "\n" +
               "Cliente  : " + nombreCliente + "\n" +
               "Balance  : $" + String.format("%.2f", balance);
    }

    @Override
    public String toString() {
        return "CuentaBancaria{num=" + numeroCuenta + ", cliente=" + nombreCliente +
               ", balance=" + balance + "}";
    }
}

class CuentaCheques extends CuentaBancaria {

    public CuentaCheques(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad > 0) {
            balance -= cantidad; 
            return true;
        }
        return false;
    }

    @Override
    public String mostrarEstado() {
        return super.mostrarEstado() + "\n" +
               "Tipo     : Cheques (permite sobregiro)";
    }

    @Override
    public String toString() {
        return "CuentaCheques{num=" + getNumeroCuenta() + ", cliente=" + getNombreCliente() +
               ", balance=" + getBalance() + "}";
    }
}

class CuentaAhorros extends CuentaBancaria {
    private double tasaInteres;

    public CuentaAhorros(String numeroCuenta, String nombreCliente, double tasaInteres) {
        super(numeroCuenta, nombreCliente);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() { return tasaInteres; }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && balance >= cantidad) {
            balance -= cantidad;
            return true;
        }
        System.out.println("Fondos insuficientes en cuenta " + getNumeroCuenta());
        return false;
    }

    public void calcularInteresMensual() {
        double interes = balance * (tasaInteres / 100);
        balance += interes;
    }

    @Override
    public String mostrarEstado() {
        return super.mostrarEstado() + "\n" +
               "Tipo     : Ahorros\n" +
               "Interes  : " + tasaInteres + "%";
    }

    @Override
    public String toString() {
        return "CuentaAhorros{num=" + getNumeroCuenta() + ", cliente=" + getNombreCliente() +
               ", tasa=" + tasaInteres + ", balance=" + getBalance() + "}";
    }
}

class CuentaPlatino extends CuentaAhorros {

    public CuentaPlatino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente, 10.0);
    }

    @Override
    public String mostrarEstado() {
        return super.mostrarEstado() + "\n" +
               "Subtipo  : Platino (10% interes, sin cargos)";
    }

    @Override
    public String toString() {
        return "CuentaPlatino{num=" + getNumeroCuenta() + ", cliente=" + getNombreCliente() +
               ", balance=" + getBalance() + "}";
    }
}

public class Problema_6_EjecutorBanco {
    public static void main(String[] args) {

        CuentaCheques cc1 = new CuentaCheques("CC-001", "Ana Garcia");
        CuentaCheques cc2 = new CuentaCheques("CC-002", "Pedro Soto");
        CuentaAhorros ca1 = new CuentaAhorros("CA-001", "Lucia Perez", 5.0);
        CuentaAhorros ca2 = new CuentaAhorros("CA-002", "Jorge Mora", 3.5);
        CuentaPlatino cp1 = new CuentaPlatino("CP-001", "Elena Vidal");
        CuentaPlatino cp2 = new CuentaPlatino("CP-002", "Marcos Leon");

        ArrayList<CuentaBancaria> cuentas = new ArrayList<>();
        cuentas.add(cc1);
        cuentas.add(cc2);
        cuentas.add(ca1);
        cuentas.add(ca2);
        cuentas.add(cp1);
        cuentas.add(cp2);

        cc1.depositar(1500.0);
        cc2.depositar(800.0);
        ca1.depositar(3000.0);
        ca2.depositar(1200.0);
        cp1.depositar(5000.0);
        cp2.depositar(2500.0);

        boolean r1 = cc1.retirar(2000.0);
        System.out.println("CC-001 retiro $2000 (sobregiro): " + (r1 ? "OK balance=" + cc1.getBalance() : "RECHAZADO"));

        boolean r2 = ca1.retirar(500.0);
        System.out.println("CA-001 retiro $500 : " + (r2 ? "OK" : "RECHAZADO"));

        boolean r3 = ca2.retirar(2000.0);
        System.out.println("CA-002 retiro $2000: " + (r3 ? "OK" : "RECHAZADO"));

        boolean r4 = cp1.retirar(1000.0);
        System.out.println("CP-001 retiro $1000: " + (r4 ? "OK" : "RECHAZADO"));

        for (int i = 0; i < cuentas.size(); i++) {
            System.out.println(cuentas.get(i).mostrarEstado());
        }

        ca1.calcularInteresMensual();
        ca2.calcularInteresMensual();
        cp1.calcularInteresMensual();
        cp2.calcularInteresMensual();

        for (int i = 0; i < cuentas.size(); i++) {
            System.out.println(cuentas.get(i).mostrarEstado());
        }

        for (int i = 0; i < cuentas.size(); i++) {
            System.out.println(cuentas.get(i));
        }
    }
}
/**
 * CC-001 retiro $2000 (sobregiro): OK balance=-500.0
CA-001 retiro $500 : OK
Fondos insuficientes en cuenta CA-002
CA-002 retiro $2000: RECHAZADO
CP-001 retiro $1000: OK
Cuenta   : CC-001
Cliente  : Ana Garcia
Balance  : $-500.00
Tipo     : Cheques (permite sobregiro)
Cuenta   : CC-002
Cliente  : Pedro Soto
Balance  : $800.00
Tipo     : Cheques (permite sobregiro)
Cuenta   : CA-001
Cliente  : Lucia Perez
Balance  : $2500.00
Tipo     : Ahorros
Interes  : 5.0%
Cuenta   : CA-002
Cliente  : Jorge Mora
Balance  : $1200.00
Tipo     : Ahorros
Interes  : 3.5%
Cuenta   : CP-001
Cliente  : Elena Vidal
Balance  : $4000.00
Tipo     : Ahorros
Interes  : 10.0%
Subtipo  : Platino (10% interes, sin cargos)
Cuenta   : CP-002
Cliente  : Marcos Leon
Balance  : $2500.00
Tipo     : Ahorros
Interes  : 10.0%
Subtipo  : Platino (10% interes, sin cargos)
Cuenta   : CC-001
Cliente  : Ana Garcia
Balance  : $-500.00
Tipo     : Cheques (permite sobregiro)
Cuenta   : CC-002
Cliente  : Pedro Soto
Balance  : $800.00
Tipo     : Cheques (permite sobregiro)
Cuenta   : CA-001
Cliente  : Lucia Perez
Balance  : $2625.00
Tipo     : Ahorros
Interes  : 5.0%
Cuenta   : CA-002
Cliente  : Jorge Mora
Balance  : $1242.00
Tipo     : Ahorros
Interes  : 3.5%
Cuenta   : CP-001
Cliente  : Elena Vidal
Balance  : $4400.00
Tipo     : Ahorros
Interes  : 10.0%
Subtipo  : Platino (10% interes, sin cargos)
Cuenta   : CP-002
Cliente  : Marcos Leon
Balance  : $2750.00
Tipo     : Ahorros
Interes  : 10.0%
Subtipo  : Platino (10% interes, sin cargos)
CuentaCheques{num=CC-001, cliente=Ana Garcia, balance=-500.0}
CuentaCheques{num=CC-002, cliente=Pedro Soto, balance=800.0}
CuentaAhorros{num=CA-001, cliente=Lucia Perez, tasa=5.0, balance=2625.0}
CuentaAhorros{num=CA-002, cliente=Jorge Mora, tasa=3.5, balance=1242.0}
CuentaPlatino{num=CP-001, cliente=Elena Vidal, balance=4400.0}
CuentaPlatino{num=CP-002, cliente=Marcos Leon, balance=2750.0}
 */