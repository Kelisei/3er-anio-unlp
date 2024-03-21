package ejercicios.ejercicio1;

public class Tweet implements Publicacion{
	private String texto;
	
	public Tweet(String texto) {
		this.texto = texto;
	}
	public String getTexto() {
		return this.texto;
	}
	public void limpiarse() {
		this.texto = "No esta disponible";
	}
	public static boolean valido(String texto) {
		return texto.length() > 0 && texto.length() <= 280;
	}
}
