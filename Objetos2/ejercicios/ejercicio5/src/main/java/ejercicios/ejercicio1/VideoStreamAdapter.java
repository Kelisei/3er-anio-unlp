package ejercicios.ejercicio1;

public class VideoStreamAdapter extends Media {
	@Override
	public void play() {
		VideoStream stream = new VideoStream();
		stream.reproduce();
	}
}
