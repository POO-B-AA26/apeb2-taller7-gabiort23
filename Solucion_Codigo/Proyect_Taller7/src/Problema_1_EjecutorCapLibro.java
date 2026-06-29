
/**
 *
 * @author Desconocido
 */
import java.util.ArrayList;

class Capitulo {
    private String titulo;
    private ArrayList<Seccion> secciones;

    public Capitulo(String titulo) {
        this.titulo = titulo;
        secciones = new ArrayList<>();
    }

    public void agregarSeccion(Seccion seccion) {
        secciones.add(seccion);
    }

    public String getTitulo() {
        return titulo;
    }

    public ArrayList<Seccion> getSecciones() {
        return secciones;
    }
}

class Seccion {
    private String titulo;
    private ArrayList<ComponenteSeccion> componentes;

    public Seccion(String titulo) {
        this.titulo = titulo;
        componentes = new ArrayList<>();
    }

    public void agregarComponente(ComponenteSeccion componente) {
        componentes.add(componente);
    }

    public String getTitulo() {
        return titulo;
    }

    public ArrayList<ComponenteSeccion> getComponentes() {
        return componentes;
    }
}

class ComponenteSeccion {
    protected int orden;

    public ComponenteSeccion(int orden) {
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }
}

class Figura extends ComponenteSeccion {
    private String descripcion;

    public Figura(int orden, String descripcion) {
        super(orden);
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

class Tabla extends ComponenteSeccion {
    private int filas;

    public Tabla(int orden, int filas) {
        super(orden);
        this.filas = filas;
    }

    public int getFilas() {
        return filas;
    }
}

class Lista extends ComponenteSeccion {
    private String tipo;

    public Lista(int orden, String tipo) {
        super(orden);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}

class Parrafo extends ComponenteSeccion {
    private int numero;
    private ArrayList<Sentencia> sentencias;

    public Parrafo(int orden, int numero) {
        super(orden);
        this.numero = numero;
        sentencias = new ArrayList<>();
    }

    public void agregarSentencia(Sentencia sentencia) {
        sentencias.add(sentencia);
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<Sentencia> getSentencias() {
        return sentencias;
    }
}

class Sentencia {
    private String texto;
    private ArrayList<Palabra> palabras;

    public Sentencia(String texto) {
        this.texto = texto;
        palabras = new ArrayList<>();
    }

    public void agregarPalabra(Palabra palabra) {
        palabras.add(palabra);
    }

    public String getTexto() {
        return texto;
    }

    public ArrayList<Palabra> getPalabras() {
        return palabras;
    }
}

class Palabra {
    private String valor;

    public Palabra(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
public class Problema_1_EjecutorCapLibro {

    public static void main(String[] args) {

        Capitulo capitulo = new Capitulo("Programacion");

        Seccion seccion = new Seccion("POO");

        Figura figura = new Figura(1, "Diagrama UML");
        Tabla tabla = new Tabla(2, 5);
        Lista lista = new Lista(3, "Numerada");
        Parrafo parrafo = new Parrafo(4, 1);

        Sentencia sentencia = new Sentencia("Java es orientado a objetos.");
        sentencia.agregarPalabra(new Palabra("Java"));
        sentencia.agregarPalabra(new Palabra("POO"));

        parrafo.agregarSentencia(sentencia);

        seccion.agregarComponente(figura);
        seccion.agregarComponente(tabla);
        seccion.agregarComponente(lista);
        seccion.agregarComponente(parrafo);

        capitulo.agregarSeccion(seccion);

        System.out.println("Capitulo: " + capitulo.getTitulo());
        System.out.println("Secciones: " + capitulo.getSecciones().size());
        System.out.println("Componentes: " + seccion.getComponentes().size());
        System.out.println("Sentencias: " + parrafo.getSentencias().size());
        System.out.println("Palabras: " + sentencia.getPalabras().size());
    }
}
/**
 * Capitulo: Programacion
Secciones: 1
Componentes: 4
Sentencias: 1
Palabras: 2
 */