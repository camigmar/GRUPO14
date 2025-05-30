
package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Cliente {
      
   private String rut;
   private String nombre;
   private String apellidoPaterno;
   private String apellidoMaterno;
   private String domicilio;
   private String comuna;
   private String telefono;
   
   static HashSet<Integer> cuentasUsadas = new HashSet<>();

   //CONSTRUCTOR VACIO
    public Cliente() {
    }

    //CONSTRUCTOR CON PARAMETROS
    public Cliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, String comuna, String telefono) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.comuna = comuna;
        this.telefono = telefono;     
    }

    //GETTERS AND SETTERS
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    //METODO PARA Validar contacto
      private  String verificarContacto(Scanner sc){
        int tel ;
        String contacto;
        String pase = " ";
         //validar rut
        System.out.println("Ingrese el numero de telefono.\nEn caso de no poseer ingrese el numero 1 y presione enter");
        do {
            tel = Cuenta.validarEntero(sc);
            contacto = String.valueOf(tel);
            
            if (contacto.length() == 7 ){
                contacto= "+562 "+contacto;
                pase = "valido";
            }else if(contacto.length() == 8){
                contacto= "+569 "+contacto;
                pase = "valido";
            }else if (contacto.equals("1") ){
            contacto = "No registra";
            pase = "valido";
            }else System.out.println("Campo invalido, el numero debe ser de 7 u 8 digitos.");
        }while (!pase.equals("valido"));
        sc.nextLine();
        
        return contacto;
    }
    
    
    //METODO PARA VALIDAR RUT
    private String[] verificarRut(Scanner sc){
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
        
        //validar digito verificador
        String digitoVerificador;
        System.out.println("Ahora ingresa el digito verificador");
        while (true){
            digitoVerificador = sc.nextLine();

            if (
                !digitoVerificador.equals("1") &&
                !digitoVerificador.equals("2") &&
                !digitoVerificador.equals("3") &&
                !digitoVerificador.equals("4") &&
                !digitoVerificador.equals("5") &&
                !digitoVerificador.equals("6") &&
                !digitoVerificador.equals("7") &&
                !digitoVerificador.equals("8") &&
                !digitoVerificador.equals("9") &&
                !digitoVerificador.equalsIgnoreCase("K")
            ) System.out.println("Digito verificador invalido, intenta nuevamente");
            else break;
        }
        rutC = rutC+"-"+digitoVerificador;
        String rutClien = String.valueOf(rutCliente);
        
        String resultado[] = {rutC,rutClien};
        return resultado ;
    }
    
    
    
    //METODO PARA REGISTRAR UN CLIENTE
    public void registrarCliente(HashMap<Integer, Cliente> clientes,HashMap<Integer, Cuenta> cuentas, Scanner sc){
        Cliente cliente = new Cliente(); 
        String valores[] = cliente.verificarRut(sc);
        String rutC = valores[0];
        String rut_Cliente = valores[1];
        int rutCliente = Integer.parseInt(rut_Cliente);

        //CONTROLA QUE NO SE CREE MAS DE UNA CUENTA PARA EL MISMO CLIENT
        while (true){
         if (clientes.containsKey(rutCliente)){
             System.out.println("No es posible crear la cuenta debido a que el cliente ya posee una cuenta.");
             return;
         }else break;         
        }
        
        System.out.println("Ingrese los nombres.");
        String nombres = sc.nextLine().toUpperCase();
        System.out.println("Ingrese el apellido paterno.");
        String apellido_Paterno = sc.nextLine().toUpperCase();
        System.out.println("Ingrese el apellido Materno.");
        String apellido_Materno = sc.nextLine().toUpperCase();
        System.out.println("Ingrese el domicilio (Calle/numero)");
        String direccion = sc.nextLine().toUpperCase();
        System.out.println("Ingrese la comuna a la que pertenece");
        String comuna_ = sc.nextLine().toUpperCase();
        String contacto = verificarContacto(sc);
        
        //CREACION DEL NUEVO CLIENTE
        Cliente nuevoCliente = new Cliente(rutC,nombres,apellido_Paterno,apellido_Materno,direccion,comuna_,contacto);
        
        //CREACION DE CUENTA
        
        Cuenta cuenta = Cuenta.crearCuenta(cuentasUsadas);
        
        
        // CAMBIO DE IDENTIFICADOR EN EL HASHMAP
        clientes.put(rutCliente, nuevoCliente);
        cuentas.put(rutCliente, cuenta);

        System.out.println("\n!!CLIENTE REGISTRADO CON EXITO¡¡");
        System.out.println("Su numero de cuenta es el: "+cuenta.getCuenta());
    } 
    
    //METODO PARA MOSTRAR DATOS DE UN CLIENTE
    public void mostrarCliente (Cliente rut,Cuenta cuenta){
        if (rut != null) {
            
            System.out.println("==============================");
            System.out.println("      DATOS DEL CLIENTE       ");
            System.out.println("------------------------------");
            System.out.println("RUT: "+rut.getRut());
            System.out.println("------------------------------");
            System.out.println("Nombre: "+rut.getNombre());
            System.out.println("------------------------------");
            System.out.println("Apellido Paterno: "+rut.getApellidoPaterno());
            System.out.println("------------------------------");
            System.out.println("Apellido Materno: "+ rut.getApellidoMaterno());
            System.out.println("------------------------------");
            System.out.println("Domicilio: "+ rut.getDomicilio());
            System.out.println("------------------------------");
            System.out.println("Comuna: "+ rut.getComuna());
            System.out.println("------------------------------");
            System.out.println("Numero Contacto: "+ rut.getTelefono());
            System.out.println("------------------------------");
            int cuentaProtegida = cuenta.getCuenta();
            String protecDatos = String.valueOf(cuentaProtegida);
            protecDatos = "XXXXX"+protecDatos.substring(5, 9);
            System.out.println("Numero cuenta: "+ protecDatos);
            System.out.println("==============================");
            
        }else System.out.println("\nCliente no encontrado.");     
    }  
}
