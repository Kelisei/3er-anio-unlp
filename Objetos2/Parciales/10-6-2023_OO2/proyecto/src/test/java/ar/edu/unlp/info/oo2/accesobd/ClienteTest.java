package ar.edu.unlp.info.oo2.accesobd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(50, new SinPromocion());
        cliente.agregarVehiculo(new Vehiculo(680000, 4, 36, new ContraTerceros()));
        cliente.agregarVehiculo(new Vehiculo(1200000, 5, 23, new ContraTerceros()));
    }

    @Test
    void testGetCostoAbonar() {
        assertEquals(124800, cliente.getCostoAbonar());
    }
}
