package ar.edu.unlp.info.oo2.accesobd;

import java.util.ArrayList;
import java.util.List;

public class Clase extends Componente {
    private List<String> metodos;
    private List<String> variablesDeInstancia;
    private Clase superclase;

    public Clase(Clase superclase, String nombre) {
        super(nombre);
        this.superclase = superclase;
        this.metodos = new ArrayList<>();
        this.variablesDeInstancia = new ArrayList<>();
    }
    public List<String> getMetodos() {
        return metodos;
    }
    public List<String> getVariablesDeInstancia() {
        return variablesDeInstancia;
    }
    public Clase getSuperclase() {
        return superclase;
    }
    public void agregarMetodo(String metodo) {
        this.metodos.add(metodo);
    }
    public void agregarVariableDeInstancia(String variable) {
        this.variablesDeInstancia.add(variable);
    }
    @Override
    public String imprimir() {
        String impresion = "";
        impresion += this.getSuperclase().getNombre() + "subclass: #" + this.getNombre() + "\n";
        impresion += "     instanceVariableNames: ";
        for (String variable: this.getVariablesDeInstancia()) {
            impresion += variable + " ";
        }
        impresion += "\n";
        impresion += "     methods: ";
        for (String metodo: this.getMetodos()) {
            impresion += metodo + " ";
        }
        impresion += "\n";
        return impresion;
    }
    @Override
    public int getCantidadClases() {
        return 1;
    }
    @Override
    public Paquete getMaximoPaquete(){
        return null;
    }
}
