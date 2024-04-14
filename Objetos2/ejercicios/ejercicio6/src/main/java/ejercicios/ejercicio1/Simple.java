package ejercicios.ejercicio1;

public class Simple extends Topografia {
    private double proporcionAgua;

    public Simple(double proporcionAgua) {
        this.proporcionAgua = proporcionAgua;
    }
    @Override
    public  double getProporcionAgua(){
        return this.proporcionAgua;
    }
}
