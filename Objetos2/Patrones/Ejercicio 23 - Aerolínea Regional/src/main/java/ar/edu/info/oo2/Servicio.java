package ar.edu.info.oo2;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.Map;

public abstract class Servicio{
    private static final Map<DayOfWeek, Double> rates;

    static {
        rates = new EnumMap<>(DayOfWeek.class);
        rates.put(DayOfWeek.MONDAY, 1.0);
        rates.put(DayOfWeek.TUESDAY, 1.01);
        rates.put(DayOfWeek.WEDNESDAY, 0.99);
        rates.put(DayOfWeek.THURSDAY, 0.95);
        rates.put(DayOfWeek.FRIDAY, 1.0);
        rates.put(DayOfWeek.SATURDAY, 1.01);
        rates.put(DayOfWeek.SUNDAY, 1.01);
    }


}
