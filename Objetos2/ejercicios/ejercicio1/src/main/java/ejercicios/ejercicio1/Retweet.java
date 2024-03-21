package ejercicios.ejercicio1;

public class Retweet implements Publicacion{
	private Tweet original;
	public Retweet(Tweet original) {
		this.original = original;
	}
	@Override
	public String getTexto() {
		return this.original.getTexto();
	}
	public Tweet getOriginal() {
		return this.original;
	}

}
