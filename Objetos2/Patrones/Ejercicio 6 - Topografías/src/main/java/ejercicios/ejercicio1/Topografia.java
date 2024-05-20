package ejercicios.ejercicio1;


public abstract class Topografia {
    public abstract double getProporcionAgua();

    public double getProporcionTierra() {
        return 1 - this.getProporcionAgua();
    }

    public boolean esIgual(Topografia topografia) {
        return this.getProporcionAgua() == topografia.getProporcionAgua();
    }

    @Override
    public boolean equals(Object o) {
        return this.esIgual((Topografia) o);
    }

    public Object getTopografias() {
        return this;
    }
}
