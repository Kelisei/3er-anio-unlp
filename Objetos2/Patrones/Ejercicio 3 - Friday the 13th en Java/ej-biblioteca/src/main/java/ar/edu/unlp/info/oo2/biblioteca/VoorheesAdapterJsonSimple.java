package ar.edu.unlp.info.oo2.biblioteca;

import java.util.List;
import org.json.simple.*;
public class VoorheesAdapterJsonSimple extends VoorheesExporter {
	
	@SuppressWarnings("unchecked")
	private JSONObject exportar(Socio socio) {
		JSONObject socioJson = new JSONObject();
		socioJson.put("nombre", socio.getNombre());
		socioJson.put("email", socio.getEmail());
		socioJson.put("legajo", socio.getLegajo());
		return socioJson;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String exportar(List<Socio> socios) {
		JSONArray jsonSocios = new JSONArray();
		List<JSONObject> socioObjects = socios.stream().map(s -> exportar(s)).toList();
		socioObjects.stream().forEach(s -> jsonSocios.add(s));
		return jsonSocios.toJSONString();
		
	}
}
