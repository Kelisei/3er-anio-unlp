package ejercicios.ejercicio2;
public interface Jugada {
	public String jugarContraPapel();
	public String jugarContraPiedra();
	public String jugarContraTijera();
	public String jugarContraMi(Jugada jugada);
}
