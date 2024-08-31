package ar.edu.unlp.info.oo2.accesobd;

public class PorMultiplesPolizas extends Promocion {
    @Override
    public double aplicarDescuento(Cliente cliente){
        double descuento = 1;
        if (cliente.getNumeroDeVehiculos() > 2) {
            descuento = 0.9;
        }
        return cliente.getCostoSeguros() * descuento;
    }
}
