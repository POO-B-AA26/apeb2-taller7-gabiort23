/**
 * Problema 03: Sistema de envío de mensajes a móviles.
 * Revisado y mejorado con apoyo de IA (Claude).
 * Mejoras aplicadas: encapsulamiento, visualizarMensaje() con lógica propia.
 * @author Gabriela Ortega
 * @version 1.0
 */
class Movil {
    private String numeroMovil;
    private String nombre;

    public Movil(String numeroMovil, String nombre) {
        this.numeroMovil = numeroMovil;
        this.nombre = nombre;
    }

    public Movil(String numeroMovil) {
        this.numeroMovil = numeroMovil;
        this.nombre = null;
    }

    public String getNumeroMovil() { return numeroMovil; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        if (nombre != null)
            return "Movil{numero=" + numeroMovil + ", nombre=" + nombre + "}";
        return "Movil{numero=" + numeroMovil + "}";
    }
}

class Mensaje {
    private Movil remitente;
    private Movil destinatario;

    public Mensaje(Movil remitente, Movil destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    public Movil getRemitente() { return remitente; }
    public Movil getDestinatario() { return destinatario; }

    public void enviarMensaje() {
        System.out.println("Enviando mensaje de " + remitente.getNumeroMovil() + " a " + destinatario.getNumeroMovil());
    }

    public void visualizarMensaje() {
        System.out.println("=== MENSAJE ===");
        System.out.println("De  : " + remitente);
        System.out.println("Para: " + destinatario);
    }

    @Override
    public String toString() {
        return "Mensaje{remitente=" + remitente + ", destinatario=" + destinatario + "}";
    }
}

class SMS extends Mensaje {
    private String texto;

    public SMS(String texto, Movil remitente, Movil destinatario) {
        super(remitente, destinatario);
        this.texto = texto;
    }

    public String getTexto() { return texto; }

    @Override
    public void enviarMensaje() {
        System.out.println("[SMS] Enviando texto de " + getRemitente().getNumeroMovil() + " a " + getDestinatario().getNumeroMovil());
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("=== SMS ===");
        System.out.println("De  : " + getRemitente());
        System.out.println("Para: " + getDestinatario());
        System.out.println("Texto: " + texto);
    }

    @Override
    public String toString() {
        return "SMS{texto='" + texto + "', " + super.toString() + "}";
    }
}

class MMS extends Mensaje {
    private String nombreFichero;

    public MMS(String nombreFichero, Movil remitente, Movil destinatario) {
        super(remitente, destinatario);
        this.nombreFichero = nombreFichero;
    }

    public String getNombreFichero() { return nombreFichero; }

    @Override
    public void enviarMensaje() {
        System.out.println("[MMS] Enviando imagen '" + nombreFichero + "' de " + getRemitente().getNumeroMovil() + " a " + getDestinatario().getNumeroMovil());
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("=== MMS ===");
        System.out.println("De     : " + getRemitente());
        System.out.println("Para   : " + getDestinatario());
        System.out.println("Fichero: " + nombreFichero);
    }

    @Override
    public String toString() {
        return "MMS{fichero='" + nombreFichero + "', " + super.toString() + "}";
    }
}

public class Problema_3_EjecutorMensajeria {
    public static void main(String[] args) {
        Movil movil1 = new Movil("0991234567", "Ariadna Ortega");
        Movil movil2 = new Movil("0987654321", "Maria Ortega");
        Movil movil3 = new Movil("0976543210");

        SMS sms1 = new SMS("Holaaa", movil1, movil2);
        sms1.enviarMensaje();
        sms1.visualizarMensaje();

        MMS mms1 = new MMS("fotoVacaciones", movil2, movil3);
        mms1.enviarMensaje();
        mms1.visualizarMensaje();

        SMS sms2 = new SMS("Reunion 10am", movil3, movil1);
        sms2.enviarMensaje();
        sms2.visualizarMensaje();
    }
}

/**
[SMS] Enviando texto de 0991234567 a 0987654321
=== SMS ===
De  : Movil{numero=0991234567, nombre=Ariadna Ortega}
Para: Movil{numero=0987654321, nombre=Maria Ortega}
Texto: Holaaa
[MMS] Enviando imagen 'fotoVacaciones' de 0987654321 a 0976543210
=== MMS ===
De     : Movil{numero=0987654321, nombre=Maria Ortega}
Para   : Movil{numero=0976543210}
Fichero: fotoVacaciones
[SMS] Enviando texto de 0976543210 a 0991234567
=== SMS ===
De  : Movil{numero=0976543210}
Para: Movil{numero=0991234567, nombre=Ariadna Ortega}
Texto: Reunion 10am
 */