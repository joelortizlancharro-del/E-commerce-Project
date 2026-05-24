import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import utils.ConnexioBD;

public class App {
    static ConnexioBD conexio=null;
    Scanner sc = new Scanner(System.in);
      
    ControlJSON json = new ControlJSON();
     ClientDAO clientDAO = new ClientDAO();


    public static void main(String[] args) throws Exception {
       
        conexio = new ConnexioBD("tpv_botiga");
        
      if (conexio.establirConexio()){
        System.out.println("Conectat");
      }
      else{
        System.out.println("Error");
      }
        App p = new App();
        p.principal();
    }
    public void principal(){
        menu();
    }

    public void menu(){
        System.out.println("==============================");
        System.out.println("Benvingut a la nostre tenda!!!");
        System.out.println("==============================");
        System.out.println("1. Inicialitzar BD, JSON a Base de dades.");
        System.out.println("2. CRUD de clients.");
        System.out.println("3. CRUD de articles.");
        System.out.print("Que vols fer?");
        int num = sc.nextInt(); 
        opcions(num); //Aqui cridem el switch
    }

    public void opcions(int num){ //Aixo es el switch

        switch (num) {
            case 1:
                inicialitzarBD();
                break;

            case 2:
                menuCRUDClients();
                break;
        
            default:
                break;
        }
    }

    public void inicialitzarBD(){
        
        ArrayList<Producte> productes = json.llegirProductes();
        
        for(int j = 0; j < productes.size(); j++){
            if(productes.get(j) instanceof Pantalo){
                    Pantalo p = (Pantalo)productes.get(j);
                     conexio.insertArticle(p.getId(), p.getNom(), p.getFamilia(), p.getTallaCintura(), p.getLlargadaCamal(), p.getPreuBase(), p.getIVA(), p.getStock());
        
                    }
                    else{
                        Camisa c = (Camisa)productes.get(j);
                         conexio.insertArticle(c.getId(), c.getNom(), c.getFamilia(), c.getTallaColl(), c.getAmpladaPit(), c.getPreuBase(), c.getIVA(), c.getStock());
        
                    }

    }
}

    public void menuCRUDClients(){
        int num;
        do {
            System.out.println("=======================================");
            System.out.println("Vols realitzar canvis en els usuaris!!!");
            System.out.println("=======================================");
            System.out.println("1. Crear client.");
            System.out.println("2. Buscar client."); //afegir taula amb dos tipus de busqueda
            System.out.println("3. Actualitzar client."); 
            System.out.println("4. Eliminar client."); 
            System.out.print("Que vols fer?");
            num = sc.nextInt();
            if(num == 2){
                opcionsBuscarClient();
            }
            else{
                CRUDCLientSwitch(num);
            }
        } while (num > 4 || num < 1);
    }

    public void CRUDCLientSwitch(int num){
        
        switch (num) {
            case 1:
                crearClient(); //fet
                break;
            
            case 3:
                modificarClient();
                break;
        
            case 4:
                esborrarClient();
                break;
            default:
                break;
        }
    }

    public void opcionsBuscarClient(){
        System.out.println("====================================");
        System.out.println("Vols buscar informacio de usuaris!!!");
        System.out.println("====================================");
        System.out.println("1. Buscar per DNI.");
        System.out.println("2. Recerca de tots els usuaris.");
        System.out.print("Que vols fer?");
        int num = sc.nextInt();
        switchBuscarClients(num);
    }

    public void switchBuscarClients(int num){
        switch (num) {
            case 1:
                buscarClientID();
                break;

            case 2: 
                recercaTotsClients();
                break;
        
            default:
                break;
        }
    }

    public void buscarClientID(){
      
        System.out.println("Vols buscar un client per el seu DNI.");
        System.out.println("");
       
        String dni;
        do {
            System.out.print("Introdueix el DNI del client que vols buscar: ");
            dni = sc.next();
        } while (!demanarDNI(dni));
       
        clientDAO.consultarClient(dni);
    }

    public void recercaTotsClients(){
        clientDAO.llistarClients();
    }

    public void crearClient(){

        System.out.println("Creant client...");
        String dni;
        do {
            System.out.print("Introdueix el seu DNI: ");
            dni = sc.next();
        } while (!demanarDNI(dni));

        sc.nextLine();
        System.out.print("Introdueix el seu nom: ");
        String nom = sc.nextLine();

        String email;
        do {
           System.out.print("Introdueix el seu email: ");
           email = sc.next();
        } while (!controlEmail(email));

        String telefon;
        do {
            System.out.print("Introdueix el seu telefon: ");
            telefon = sc.next();
        } while (!controlNumero(telefon));
        
        Clients client = new Clients(dni, nom, email, telefon);

        
        clientDAO.afegirClient(client);
    }

    public void modificarClient(){
        System.out.println("Has decidit modificar un client.");
        System.out.println("");
        String dni;
        do {
            System.out.print("Introdueix el DNI del client: ");
        dni = sc.next();
        } while (!demanarDNI(dni));

        sc.nextLine();
        String nom;
        System.out.print("Introdueix el seu nom: ");
        nom = sc.nextLine();

        String email;
        do {
            System.out.print("Introdueix el seu correu: ");
            email = sc.next();
        } while (!controlEmail(email));

        String telefon;
        do {
            System.out.print("Introdueix el seu numero de telefon: ");
            telefon = sc.next();
        } while (!controlNumero(telefon));

        Clients client = new Clients(dni, nom, email, telefon);
        clientDAO.modificarClient(client);
        
    }

    public void esborrarClient(){
        System.out.println("Vols eliminar un client.");
        System.out.println("");
         String dni;
        do {
            System.out.print("Introdueix el DNI del client que vols eliminar: ");
        dni = sc.next();
        } while (!demanarDNI(dni));

        clientDAO.esborrarClient(dni);
    }

    public boolean demanarDNI(String dni){
        int llargadaDNI = dni.length();
        char ultimDNI = dni.charAt(dni.length() - 1);

        if(llargadaDNI != 9){
            System.out.println("La llargada el DNI es de 9 caracters.");
            return false;
        }

        for(int j = 0; j < llargadaDNI-1; j++){
            if(!Character.isDigit(dni.charAt(j))){
                System.out.println("Format incorrecte.");
                return false;
            }
        }
        
        if(Character.isDigit(ultimDNI)){
            System.out.println("Format incorrecte.");
            return false;
        }
        else{
            dni = dni.toUpperCase();
            return true;
        }
    }

    public boolean controlEmail(String email){
        for(int j = 0; j < email.length(); j++){
            if(email.charAt(j) == '@'){
                return true;
            }
        }
        return false;
    }

    public boolean controlNumero(String telefon){
        if(telefon.length() != 9){
            return false;
        }
        for(int j = 0; j < telefon.length(); j++){
            if(!Character.isDigit(telefon.charAt(j))){
                return false;
            }
        }
        return true;
    }
}
