
package logica;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

//CLASE  CUENTA
public abstract class Cuenta {

    private int cuenta;
    private int saldo;
    
    
    //CONSTRUCTOR
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
    
    //METODO ABTRACTO
    public abstract void mostrarTipoCuenta();
     
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
