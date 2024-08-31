package ar.edu.unlp.info.oo2.accesobd;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int edad;
    private List<Vehiculo> vehiculos;
    private Promocion promocion;

    public double getSeguroMasEconomico() {
        return vehiculos.stream().min((v1, v2) -> Double.compare(v1.costoSeguro(this), v2.costoSeguro(this)))
                .map(v -> v.costoSeguro((this))).get();
    }

    public int getNumeroDeVehiculos() {
        return vehiculos.size();
    }

    public Cliente(int edad, Promocion promocion) {
        this.edad = edad;
        this.promocion = promocion;
        this.vehiculos = new ArrayList<>();
    }

    public int getEdad() {
        return edad;
    }

    public double getCostoSeguros(){
        return vehiculos.stream().mapToDouble(v -> v.costoSeguro(this)).sum();
    }

    public double getCostoAbonar() {
        return this.promocion.aplicarDescuento(this);
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
    }
}
