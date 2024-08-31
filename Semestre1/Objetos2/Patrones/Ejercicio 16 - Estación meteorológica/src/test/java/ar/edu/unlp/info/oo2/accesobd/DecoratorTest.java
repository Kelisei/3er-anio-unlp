package ar.edu.unlp.info.oo2.accesobd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DecoratorTest {
    private HomeWeatherStationAdapter station;	


    @BeforeEach
    public void setUp() throws Exception {
        HomeWeatherStation h = new HomeWeatherStation(1008, 200, 86);
        h.agregarTemperatura(80.6);
        h.agregarTemperatura(89.6);
        h.agregarTemperatura(87.8);
        station = new HomeWeatherStationAdapter(h);
    }

    @Test
    public void testDecoradorPermisos() {
       CelsiusDecorator c = new CelsiusDecorator(station);
       PressureDecorator p = new PressureDecorator(c);
       RadiationDecorator r = new RadiationDecorator(p);
       AverageCelsiusDecorator a = new AverageCelsiusDecorator(r);
       MinCDecorator m = new MinCDecorator(a);
       MaxCDecorator x = new MaxCDecorator(m);

       assertEquals(" Temperatura C: 30.0; Presion atmosf: 1008.0; Radiación Solar: 200.0; Promedio: 30; Mínimo: 27; Máximo: 32;", x.displayData());
    }
}
