package ar.edu.unlp.info.oo2.accesobd;

public class SinPromocion extends Promocion {
    @Override
    public double aplicarDescuento(Cliente cliente) {
        return cliente.getCostoSeguros();
    }
}
