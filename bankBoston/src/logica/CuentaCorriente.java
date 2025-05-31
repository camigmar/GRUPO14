
package logica;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class CuentaCorriente extends Cuenta implements Interfaz{
    
    private String TipoCuenta;

    public CuentaCorriente(int saldo, int cuenta, String TipoCuenta) {
        super(saldo, cuenta);
        this.TipoCuenta = TipoCuenta;
    }
    
    //CREACION DE CUENTA
    public static CuentaCorriente crearCuenta (HashSet<Integer>cuentasUsadas){
        int saldo1 = 0;
        int numeroCuenta1;
        String tipoDeCuenta1 = "Cuenta Corriente";
        while (true){  
           Random idCuenta = new Random();
           numeroCuenta1 = idCuenta.nextInt(899999999)+ 100000000 ; 
           if (!cuentasUsadas.contains(numeroCuenta1)) {
               cuentasUsadas.add(numeroCuenta1);
               break;      
           } 
        }
        
        CuentaCorriente cuentaCorriente = new CuentaCorriente(saldo1, numeroCuenta1, tipoDeCuenta1);
        return cuentaCorriente;
    }
    
    @Override
    public void mostrarTipoCuenta(){
        System.out.println("Cuenta Corriente - Nº: " + getCuenta());
    }
    
    @Override
    public void verSaldo(){
        System.out.println("=========================================================");
        System.out.println("El saldo de la cuenta es de: $"+ getSaldo() +" pesos");
        System.out.println("=========================================================");       
    }
    
    @Override
    public void giro(Scanner sc){
        System.out.println("Ingresa el monto que deseas retirar:");
        int montoActual = getSaldo();
        int montoGiro = validarEntero(sc);
        System.out.println("===================================================================");
        if (montoGiro < montoActual){
            int nuevoMonto = montoActual - montoGiro;
            setSaldo(nuevoMonto);
            
            
            System.out.println("Se ha realizado un giro de dinero");
            System.out.println("Su nuevo saldo es de: $"+ getSaldo() +" pesos");
        }else System.out.println("No tienes saldo suficiente para realizar el giro desde la cuenta"); 
        System.out.println("===================================================================");        
    }
    
    @Override
    public void deposito(Scanner sc){
        System.out.println("Ingresa el monto que deseas abonar:");
        int montoDeposito = validarEntero(sc);
        int montoActual = getSaldo();
        int nuevoMonto = montoDeposito + montoActual;
        setSaldo(nuevoMonto);
        
        System.out.println("====================================");
        System.out.println("Se ha realizado un deposito");
        System.out.println("Su nuevo saldo es de: $"+getSaldo()+" pesos");
        System.out.println("====================================");
    }        
    
}
