package ejercicios.ejercicio2;

public class Papel implements Jugada {

	@Override
	public String jugarContraPapel() {
		return "Empate";
	}

	@Override
	public String jugarContraPiedra() {
		return "Victoria";
	}

	@Override
	public String jugarContraTijera() {
		return "Derrota";
	}

	@Override
	public String jugarContraMi(Jugada jugada) {
		return jugada.jugarContraPapel();
	}

}
