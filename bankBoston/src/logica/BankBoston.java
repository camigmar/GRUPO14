
package logica;

import java.util.HashMap;
import java.util.Scanner;


public class BankBoston {
    
    // METODO PARA MOSTRAR MENU
    private static int menu (Scanner sc){ 
        System.out.println("\n\nIngresa el numero de la opcion que deseas\n");
        System.out.println("1._Registro de clientes.\n2._Buscar cliente\n3._Consultar saldo\n4._Depositar dinero\n5._Girar dinero\n6._Cerrar programa");
        while (true){
            int opcion = Cuenta.validarEntero(sc);
            if(opcion<7 && opcion>0) return opcion;
            else System.out.println("Debes ingresar una de las opciones mostradas");
        }
    }
    
    //metodo validacion rut para busqueda por hashmap
    private static int verificarRut(Scanner sc){
        int rutCliente;
        String rutC;
        String pase = " ";
         //validar rut
        System.out.println("\nIngrese el RUT sin puntos ni digito verificador.");
        do {
            rutCliente = Cuenta.validarEntero(sc);
            rutC = String.valueOf(rutCliente);
            
            if (rutC.length() == 7 || rutC.length() == 8) pase = "valido";
            else System.out.println("\nEl Rut debe contener entre 7 y 8 digitos\nVuelva a intentarlo");
        }while (!pase.equals("valido"));
        sc.nextLine();
        int resultado = rutCliente; 
        return resultado ;
    }
  
  
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        HashMap<Integer, Cliente> clientes = new HashMap<>();
        HashMap<Integer, Cuenta> cuentas = new HashMap<>();
        Cuenta metodos = new Cuenta();
        Cliente cliente = new Cliente();
        

        //CREACION DE MENU
        while (true){
            System.out.println("\n______BIENVENIDO AL SISTEMA DE GESTION DE CLIENTES DE BOSTON BANK______");
            int opcion = menu(sc);
           
            switch (opcion){
                case 1:  // Registrar un cliente
                    cliente.registrarCliente(clientes,cuentas, sc);
                    continue;
                    
                case 2: // BUSCAR UN CLIENTE
                    int rutBuscado = verificarRut(sc);   
                    Cliente buscado = clientes.get(rutBuscado);
                    Cuenta buscar = cuentas.get(rutBuscado);
                    cliente.mostrarCliente(buscado,buscar);
                    continue;
                    
                case 3://CONSULTAR SALDO
                    int numeroCuenta = verificarRut(sc);
                    Cuenta cuentaBuscada = cuentas.get(numeroCuenta);
                    if (cuentaBuscada != null)   metodos.consultarSaldo(cuentaBuscada);
                    else System.out.println("Cuenta no encontrada."); 
                    continue;  
                    
                case 4://DEPOSITO
                    numeroCuenta = verificarRut(sc);
                    cuentaBuscada = cuentas.get(numeroCuenta);
                    if (cuentaBuscada != null) metodos.depositar(sc,cuentaBuscada);
                    else System.out.println("Cuenta no encontrada");
                    continue;
                    
                case 5://GIRO DE DINERO
                   numeroCuenta = verificarRut(sc);
                   cuentaBuscada = cuentas.get(numeroCuenta);
                   
                   if (cuentaBuscada != null) metodos.girar(sc,cuentaBuscada);
                   else System.out.println("Cuenta no encontrada");
                   continue;
                   
                case 6://Cerrar programa
                    break;
            }
            break;
        }
        System.out.println("\n\nCerrando programa....");
        System.out.println("Hasta pronto.");
    }
}
