import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class ExcepcionE1 extends Exception {
    public ExcepcionE1() {
        super(); // constructor por defecto de Exception
    }

    public ExcepcionE1(String cadena) {
        super(cadena); // constructor param. de Exception
    }
}

class ExcepcionE2 extends Exception {
    public ExcepcionE2() {
        super(); // constructor por defecto de Exception
    }

    public ExcepcionE2(String cadena) {
        super(cadena); // constructor param. de Exception
    }
}

// Esta clase lanzará la excepción
public class Evaluacion {
    void evalua(int edad) throws ExcepcionE1, ExcepcionE2 {
        if (edad < 18)
            throw new ExcepcionE1("Es una persona menor de edad");
        else if (edad > 70)
            throw new ExcepcionE2("Es persona mayor de edad");
    }

    void segmenta(int edad) throws ExcepcionE1, ExcepcionE2 {
        if (edad < 35)
            throw new ExcepcionE1("Es una persona joven");
    }
}

class AnalisisEdadPoblacion {
    public static void main(String[] args) {
        // Para leer un fichero
        Evaluacion Invoca = new Evaluacion();
        FileInputStream entrada = null;
        int leo;
        try {
            entrada = new FileInputStream("fich.txt");
            while ((leo = entrada.read()) != -1) {
                try {
                    if (leo < 0) {
                        throw new ExcepcionE1("Edad inválida");
                    } else {
                        if (leo > 120) {
                            throw new ExcepcionE1("Edad inválida");
                        }
                    }
                    Invoca.evalua(leo);
                    Invoca.segmenta(leo);
                    System.out.println("Es persona adulta, Todo fue bien");
                } catch (ExcepcionE2 e) {
                    System.out.println("Excepcion: " + e.getMessage());
                } catch (ExcepcionE1 e) {
                    System.out.println("Excepcion: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e1) {
            System.out.println("No se encontró el archivo");
        } catch (IOException e) {
            System.out.println("Problema para leer los datos");
        } finally {
            if (entrada != null)
                try {
                    entrada.close();
                } catch (Exception e) {
                    System.out.println("Excepcion: " + e.getMessage());
                }
            System.out.println("Fichero cerrado.");
        }
    }
}