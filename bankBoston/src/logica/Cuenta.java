
package logica;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

//CLASE  CUENTA
public class Cuenta {

    private int cuenta;
    private int saldo;
    
    //CONSTRUCTOR VACIO
    public Cuenta(){}
    
    //CONSTRUCTOR CON PARAMETROS
    public Cuenta(int saldo, int cuenta) {
        this.saldo = saldo;
        this.cuenta = cuenta;
    }

   //GETTERS AND SETTERS
     public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }
    
    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
    
    //CREACION DE CUENTA
        public static Cuenta crearCuenta (HashSet<Integer>cuentasUsadas){
            int saldo = 0;
            int numeroCuenta;
            while (true){  
               Random idCuenta = new Random();
               numeroCuenta = idCuenta.nextInt(899999999)+ 100000000 ;
               if (!cuentasUsadas.contains(numeroCuenta)) {
                   cuentasUsadas.add(numeroCuenta);
                   break;      
               } 
            }
            Cuenta cuenta = new Cuenta(saldo,numeroCuenta);
            return cuenta;
        }
        
        
        
  //METODO PARA CONSULTAR SALDO
    public void consultarSaldo(Cuenta cuenta){
        System.out.println("=========================================================");
        System.out.println("El saldo de la cuenta es de: $"+cuenta.getSaldo()+" pesos");
        System.out.println("=========================================================");
    }
 
    
    //METODO PARA DEPOSITAR
    public void depositar(Scanner sc,Cuenta cuenta){
        System.out.println("Ingresa el monto que deseas abonar:");
        int montoDeposito = validarEntero(sc);
        int montoActual = cuenta.getSaldo();
        int nuevoMonto = montoDeposito+montoActual;
        cuenta.setSaldo(nuevoMonto);
        
        System.out.println("====================================");
        System.out.println("Se ha realizado un deposito");
        System.out.println("Su nuevo saldo es de: $"+cuenta.getSaldo()+" pesos");
        System.out.println("====================================");
    }
    
    
    //METODO PARA GIRAR
     public void girar(Scanner sc,Cuenta cuenta){
        System.out.println("Ingresa el monto que deseas retirar:");
        int montoActual = cuenta.getSaldo();
        int montoGiro = validarEntero(sc);
        System.out.println("===================================================================");
        if (montoGiro<montoActual){
            int nuevoMonto = montoActual-montoGiro;
            cuenta.setSaldo(nuevoMonto);
            
            
            System.out.println("Se ha realizado un giro de dinero");
            System.out.println("Su nuevo saldo es de: $"+cuenta.getSaldo()+" pesos");
        }else System.out.println("No tienes saldo suficiente para realizar el giro desde la cuenta"); 
        System.out.println("===================================================================");
    }
     
     //VALIDAR ENTERO
    public static int validarEntero (Scanner sc){
        int num;
        while (true){
            try{
                num = sc.nextInt();
                if (num > 0) break;
                else System.out.println("Debes ingresar un numero valido mayor a 0");
            }catch(java.util.InputMismatchException e){
                System.out.println("Debes ingresar solo numeros");
            sc.next();
            }
        }
        return num;
    }
}
