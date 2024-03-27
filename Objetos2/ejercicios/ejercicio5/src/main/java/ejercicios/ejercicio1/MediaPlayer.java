package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class MediaPlayer {
	private List<Media> media;
	public MediaPlayer() {
		this.media = new ArrayList<>();
	}
	public void play() {
		this.media.stream().forEach(m -> m.play());
	}
}
