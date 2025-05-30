
package logica;

public abstract class Producto {
    
    private String TipoCuenta;
    private int cargo;

    public Producto(String TipoCuenta, int cargo) {
        this.TipoCuenta = TipoCuenta;
        this.cargo = cargo;
    }
    
    
    abstract public void consultar();
    abstract public void depositar();
    abstract public void girar();
    
}
