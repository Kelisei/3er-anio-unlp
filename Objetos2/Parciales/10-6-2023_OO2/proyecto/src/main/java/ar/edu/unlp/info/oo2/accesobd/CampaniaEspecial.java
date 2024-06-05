package ar.edu.unlp.info.oo2.accesobd;

public class CampaniaEspecial extends Promocion {
    @Override
    public double aplicarDescuento(Cliente cliente) {
        return cliente.getCostoAbonar() - (cliente.getSeguroMasEconomico() / 2);
    }
}
