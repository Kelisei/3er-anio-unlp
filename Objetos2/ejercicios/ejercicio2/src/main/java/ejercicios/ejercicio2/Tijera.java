package ejercicios.ejercicio2;

public class Tijera implements Jugada {

	@Override
	public String jugarContraPapel() {
		return "Victoria";
	}

	@Override
	public String jugarContraPiedra() {
		return "Derrota";
	}

	@Override
	public String jugarContraTijera() {
		return "Empate";
	}

	@Override
	public String jugarContraMi(Jugada jugada) {
		return jugada.jugarContraTijera();
	}

}
