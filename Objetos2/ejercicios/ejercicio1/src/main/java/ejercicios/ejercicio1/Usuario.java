package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String screenName;
	private List<Tweet> tweets;
	private List<Retweet> retweets;
	
	public Usuario(String name) {
		this.screenName = name;
		this.tweets = new ArrayList<>();
		this.retweets = new ArrayList<>();
	}
	
	public String getName() {
		return this.screenName;
	}
	
	public Publicacion crearTweet(String texto) {
		Tweet nuevo = null;
		if (Tweet.valido(texto)) {
			nuevo = new Tweet(texto);
			this.tweets.add(nuevo);
		}
		return nuevo;
	}
	
	public Publicacion crearRetweet(Tweet original) {
		Retweet nuevo = new Retweet(original);
		this.retweets.add(nuevo);
		return nuevo;
	}
	
	public List<Publicacion> getPublicaciones(){
		List<Publicacion> lista = new ArrayList<>();
		lista.addAll(tweets);
		lista.addAll(retweets);
		return lista;
	}
	public void eliminarPublicaciones() {
		this.retweets.clear();
		this.tweets.stream().forEach(t -> t.limpiarse());
		this.tweets.clear();
	}
}