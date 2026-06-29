
import java.util.ArrayList;



/**
 *Problema02: Un videoclub dispone de una serie de películas que pueden estar en DVD 
 * (con capacidad en Gb.) o en VHS (una sola cinta por película y con cierto tipo de 
 * cinta magnetica). De las películas interesa guardar el título, el autor, el año de
 * edición y el idioma (o los idiomas, en caso de DVD). El precio de alquiler de las 
 * películas varía en función del tipo de película. Las DVD siempre son 10% mas caras
 * que las de VHS.
 * @author Gabriela Ortega
 * @version 1.0
 */
class Pelicula {
    private String titulo;
    private String autor;
    private int anioEdicion;
    private double precioAlquiler;

    public Pelicula(String titulo, String autor, int anioEdicion, double precioAlquiler) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioEdicion = anioEdicion;
        this.precioAlquiler = precioAlquiler;
    }

    public String getTitulo() { 
        return titulo; 
    }
    public String getAutor() { 
        return autor; 
    }
    public int getAnioEdicion() { 
        return anioEdicion; 
    }
    public double getPrecioAlquiler() { 
        return precioAlquiler; 
    }
    

    public String mostrarInfo() {
        return "Titulo   : " + titulo + "\n" +
               "Autor    : " + autor + "\n" +
               "Anio     : " + anioEdicion + "\n" +
               "Precio   : $" + precioAlquiler;
    }

    @Override
    public String toString() {
        return "Pelicula{titulo=" + titulo + ", autor=" + autor +
               ", anio=" + anioEdicion + ", precio=" + precioAlquiler + "}";
    }
}

class VHS extends Pelicula {
    private String tipoCinta;

    public VHS(String titulo, String autor, int anioEdicion, double precioAlquiler, String tipoCinta) {
        super(titulo, autor, anioEdicion, precioAlquiler);
        this.tipoCinta = tipoCinta;
    }

    public String getTipoCinta() { return tipoCinta; }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo() + "\n" +
               "Tipo     : VHS\n" +
               "Cinta    : " + tipoCinta;
    }

    @Override
    public String toString() {
        return "VHS{titulo=" + getTitulo() + ", tipoCinta=" + tipoCinta +
               ", precio=" + getPrecioAlquiler() + "}";
    }
}

class DVD extends Pelicula {
    private double capacidadGB;
    private ArrayList<String> idiomas;

    public DVD(String titulo, String autor, int anioEdicion, double precioAlquiler, double capacidadGB) {
        super(titulo, autor, anioEdicion, precioAlquiler * 1.10);
        this.capacidadGB = capacidadGB;
        this.idiomas = new ArrayList<>();
    }

    public double getCapacidadGB() { return capacidadGB; }
    public ArrayList<String> getIdiomas() { return idiomas; }
    public void agregarIdioma(String idioma) { idiomas.add(idioma); }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo() + "\n" +
               "Tipo     : DVD\n" +
               "Capacidad: " + capacidadGB + " GB\n" +
               "Idiomas  : " + idiomas;
    }

    @Override
    public String toString() {
        return "DVD{titulo=" + getTitulo() + ", capacidad=" + capacidadGB +
               ", idiomas=" + idiomas + ", precio=" + getPrecioAlquiler() + "}";
    }
}

public class Problema_2_EjecutorVideoClub {
    public static void main(String[] args) {

        VHS vhs1 = new VHS("El Padrino", "Francis Ford Coppola", 1972, 2.50, "Betamax");
        VHS vhs2 = new VHS("Titanic", "James Cameron", 1997, 2.00, "VHS-C");

        DVD dvd1 = new DVD("Avengers Endgame", "Anthony Russo", 2019, 3.0, 8.5);
        dvd1.agregarIdioma("Espanol");
        dvd1.agregarIdioma("Ingles");
        dvd1.agregarIdioma("Frances");

        DVD dvd2 = new DVD("Inception", "Christopher Nolan", 2010, 2.80, 4.7);
        dvd2.agregarIdioma("Ingles");
        dvd2.agregarIdioma("Espanol");

        System.out.println(vhs1.mostrarInfo());
        System.out.println(vhs2.mostrarInfo());
        System.out.println(dvd1.mostrarInfo());
        System.out.println(dvd2.mostrarInfo());

        System.out.println(vhs1);
        System.out.println(vhs2);
        System.out.println(dvd1);
        System.out.println(dvd2);
    }
}
/**
 * Titulo   : El Padrino
Autor    : Francis Ford Coppola
Anio     : 1972
Precio   : $2.5
Tipo     : VHS
Cinta    : Betamax
Titulo   : Titanic
Autor    : James Cameron
Anio     : 1997
Precio   : $2.0
Tipo     : VHS
Cinta    : VHS-C
Titulo   : Avengers Endgame
Autor    : Anthony Russo
Anio     : 2019
Precio   : $3.3000000000000003
Tipo     : DVD
Capacidad: 8.5 GB
Idiomas  : [Espanol, Ingles, Frances]
Titulo   : Inception
Autor    : Christopher Nolan
Anio     : 2010
Precio   : $3.08
Tipo     : DVD
Capacidad: 4.7 GB
Idiomas  : [Ingles, Espanol]
VHS{titulo=El Padrino, tipoCinta=Betamax, precio=0.0}
VHS{titulo=Titanic, tipoCinta=VHS-C, precio=0.0}
DVD{titulo=Avengers Endgame, capacidad=8.5, idiomas=[Espanol, Ingles, Frances], precio=0.0}
DVD{titulo=Inception, capacidad=4.7, idiomas=[Ingles, Espanol], precio=0.0}
 */