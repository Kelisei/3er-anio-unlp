package ar.edu.unlp.info.oo2.accesobd;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private List<Cliente> clientes;

    public Empresa() {
        this.clientes = new ArrayList<>();
    }

    public Cliente agregarCliente(int edad, Promocion promocion) {
        Cliente cliente = new Cliente(edad, promocion);
        this.clientes.add(cliente);
        return cliente;
    }

    public void cambiarPromocion(Cliente cliente, Promocion promocion) {
        cliente.setPromocion(promocion);
    }

    public double getCostoAbonar() {
        return this.clientes.stream().mapToDouble(c -> c.getCostoAbonar()).sum();
    }

    public Vehiculo agregarVehiculo(double valor, int capacidad, double antiguedad, Seguro seguro, Cliente cliente) {
        Vehiculo vehiculo = new Vehiculo(valor, capacidad, antiguedad, seguro);
        cliente.agregarVehiculo(vehiculo);
        return vehiculo;
    }

}
