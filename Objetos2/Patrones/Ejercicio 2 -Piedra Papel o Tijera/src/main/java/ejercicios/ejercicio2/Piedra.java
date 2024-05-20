package ejercicios.ejercicio2;

public class Piedra implements Jugada {

	@Override
	public String jugarContraPapel() {
		return "Derrota";
	}

	@Override
	public String jugarContraPiedra() {
		return "Empate";
	}

	@Override
	public String jugarContraTijera() {
		return "Victoria";
	}

	@Override
	public String jugarContraMi(Jugada jugada) {
		return jugada.jugarContraPiedra();
	}

}
