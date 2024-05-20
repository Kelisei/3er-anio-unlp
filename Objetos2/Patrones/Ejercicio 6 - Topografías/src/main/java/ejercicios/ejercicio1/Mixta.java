package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Mixta extends Topografia {
    private List<Topografia> topografias;

    public Mixta(Topografia topo1, Topografia topo2, Topografia topo3, Topografia topo4) {
        this.topografias = new ArrayList<Topografia>();
        this.topografias.add(topo1);
        this.topografias.add(topo2);
        this.topografias.add(topo3);
        this.topografias.add(topo4);
    }
    @Override
    public  double getProporcionAgua(){
        return this.topografias.stream().mapToDouble(Topografia::getProporcionAgua).sum()/4;
    }
    @Override
    public List<Topografia> getTopografias() {
        return this.topografias;
    }

    @Override
    public boolean esIgual(Topografia topografia) {
        return this.getTopografias().equals(topografia.getTopografias());
    }
}
