package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
	private List<Usuario> usuarios;
	
	public Sistema() {
		usuarios = new ArrayList<>();
	}
	
	public Usuario crearUsuario(String screenName) {
		Usuario nuevo = null;
		if (!usuarios.stream().anyMatch(u -> u.getName().equals(screenName))){
			nuevo = new Usuario(screenName);
			this.usuarios.add(nuevo);
		}
		return nuevo;
	}
	
	public Publicacion crearTweet(Usuario usuario, String texto) {
		return usuario.crearTweet(texto);
	}
	public Publicacion crearRetweet(Usuario usuario, Tweet original) {
		return usuario.crearRetweet(original);
	}
	public void eliminarUsuario(Usuario usuario) {
		usuario.eliminarPublicaciones();
		this.usuarios.remove(usuario);
	}
}
