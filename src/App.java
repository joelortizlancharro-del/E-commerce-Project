import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import utils.ConnexioBD;
import utils.Venta;

public class App {
    static ConnexioBD conexio=null;
    Scanner sc = new Scanner(System.in);
      
    ControlJSON json = new ControlJSON();
     ClientDAO clientDAO = new ClientDAO();
     Venta ventes = new Venta();
     vendesPerArticle ventesArticle = new vendesPerArticle();
     VendesClientDAO vendesPerClient = new VendesClientDAO();


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
        System.out.println("1. CRUD de clients.");
        System.out.println("2. CRUD de articles.");
        System.out.println("3. Registrar vendes.");
        System.out.println("4. Consulta tiquets");
        System.out.println("5. Sortir");
        System.out.print("Que vols fer?");
        int num = sc.nextInt(); 
        opcions(num); //Aqui cridem el switch
    }

    public void opcions(int num){ //Aixo es el switch

        switch (num) {
            case 1:
                 menuCRUDClients();
                break;
            case 2:
                menuCRUDArticles();
                break;

            case 3: 
                menuVendes();
                break;

            case 4:
                menuConsultes();
                break;
            case 5: 
                System.exit(0);

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

    public void menuCRUDArticles(){
        int num;
        do {
            System.out.println("========================================");
            System.out.println("Vols realitzar canvis en els articles!!!");
            System.out.println("========================================");
            System.out.println("1. Crear article.");
            System.out.println("2. Buscar article."); //afegir taula amb dos tipus de busqueda
            System.out.println("3. Actualitzar article."); 
            System.out.println("4. Eliminar article."); 
            System.out.print("Que vols fer?");
            num = sc.nextInt();
            if(num == 2){
                opcionsBuscarArticle();
            }
            else{
                CRUDArticlesSwitch(num);
            }
        } while (num > 4 || num < 1);
    }
    
    public void menuConsultes(){
        int opcio;
        do {
            System.out.println("Vols fer consulta de ventes per article i per usuari.");
            System.out.println("1.Recerca per DNI.");
            System.out.println("2.Rcerca per id de article");
            opcio = controlInt();
        } while (opcio > 2 || opcio < 1);
        
        switch (opcio) {
            case 1:
                recercaPerDNI();
                break;

            case 2:
                recercaPerID();
                break;
        
            default:
                break;
        }

    }

    public void CRUDCLientSwitch(int num){
        
        switch (num) {
            case 1:
                crearClient();
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

    public void CRUDArticlesSwitch(int num){
        switch (num) {
            case 1:
                crearArticle();
                break;
            
            case 3:
                modificarArticle();
                break;
        
            case 4:
                esborrarArticle();
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

    public void opcionsBuscarArticle(){
        System.out.println("====================================");
        System.out.println("Vols buscar informacio de articles!!!");
        System.out.println("====================================");
        System.out.println("1. Buscar per ID.");
        System.out.println("2. Recerca de tots els articles.");
        System.out.print("Que vols fer?");
        int num = sc.nextInt();
        switchBuscarArticles(num);
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

    public void switchBuscarArticles(int num){
        switch (num) {
            case 1:
                buscarArticleID();
                break;

            case 2: 
                recercaTotsArticles();
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
        menu();
    }

    public void buscarArticleID(){
        System.out.println("Vols buscar un article per ID.");
        System.out.println("");
        System.out.println("Introdueix el ID del article que vols buscar: ");
        int id = sc.nextInt();

        conexio.selectArticlesById(id);
        menu();
    }

    public void recercaTotsClients(){
        clientDAO.llistarClients();
        menu();
    }

    public void recercaTotsArticles(){
        conexio.selectArticles();
        menu();
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
        menu();
    }

    public void crearArticle(){
       System.out.println("Creant article...");
        int id;
        System.out.print("Introdueix el ID del nou article: ");
        id = sc.nextInt();

        String nom;
        System.out.print("Introdueix el nom del article: ");
        nom = sc.nextLine();

        int familia;
        do {
            System.out.println("Introdueix la familia: ");
            System.out.println("1. Camisa.");
            System.out.println("2. Pantalo");
            familia = sc.nextInt();
        } while (!controlFamilia(familia));

        double preu_base;
        System.out.print("Introdueix el preu base: ");
        preu_base = sc.nextDouble();

        int iva;
        do {
            System.out.print("Introdueix IVA: ");
            iva = sc.nextInt();
        } while (iva < 0);

        int stock;
        do {
            System.out.print("Introdueix stock: ");
            stock = sc.nextInt();
        } while (stock < 0);

        if(familia == 1){
           
            System.out.println("Introdueix talla coll");
            int talla_coll = sc.nextInt();
           
            if(talla_coll > 52){
                talla_coll = 52;
                System.out.println("Talla de coll: 52.");
            }
            else if(talla_coll < 36){
                talla_coll = 36;
                System.out.println("Talla de coll: 36.");
            }

            System.out.println("Introdueix amplada pit");
            int amplada_pit = sc.nextInt();
            
            if(amplada_pit > 15){
                amplada_pit = 15;
                System.out.println("Amplada pit: 15.");
            }
            else if(amplada_pit < 10){
                amplada_pit = 10;
                System.out.println("Amplada pit: 10.");
            }

            conexio.insertArticle(id, nom, familia, talla_coll, amplada_pit, preu_base, iva, stock); //quan faci merge amb el develop no hi haura error
        }
        else if(familia == 2){
            
            System.out.println("Introdueix talla cintura");
            int talla_cintura = sc.nextInt();
           
            if(talla_cintura > 56){
                talla_cintura = 56;
                System.out.println("Talla cintura: 56.");
            }
            else if(talla_cintura < 24){
                talla_cintura = 24;
                System.out.println("Talla cintura: 24.");
            }

            System.out.println("Introdueix llargada camal");
            int llargada_camal = sc.nextInt();
            
            if(llargada_camal > 46){
                llargada_camal = 46;
                System.out.println("Llargada camal: 46.");
            }
            else if(llargada_camal < 32){
                llargada_camal = 32;
                System.out.println("Llargada camal: 32.");
            }

            conexio.insertArticle(id, nom, familia, talla_cintura, llargada_camal, preu_base, iva, stock);  //quan faci el merge quedara correcte ja que en el origin develop el familia es int
        }
        menu();
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
        menu();
        
    }

    public void modificarArticle(){ //preguntar a Albert si es pot cambiar de tipus de familia
        System.out.println("Vols modificar un article");
        System.out.println("");
        System.out.print("Introdueix el ID del article a modificar: ");
        int id = sc.nextInt();

        String nom;
        System.out.print("Introdueix el nom del article: ");
        nom = sc.nextLine();

        int familia;
        do {
            System.out.println("Introdueix la familia: ");
            System.out.println("1. Camisa.");
            System.out.println("2. Pantalo");
            familia = sc.nextInt();
        } while (!controlFamilia(familia));

        double preu_base;
        System.out.print("Introdueix el preu base: ");
        preu_base = sc.nextDouble();

        int iva;
        do {
            System.out.print("Introdueix IVA: ");
            iva = sc.nextInt();
        } while (iva < 0);

        int stock;
        do {
            System.out.print("Introdueix stock: ");
            stock = sc.nextInt();
        } while (stock < 0);

        if(familia == 1){
           
            System.out.println("Introdueix talla coll");
            int talla_coll = sc.nextInt();
           
            if(talla_coll > 52){
                talla_coll = 52;
                System.out.println("Talla de coll: 52.");
            }
            else if(talla_coll < 36){
                talla_coll = 36;
                System.out.println("Talla de coll: 36.");
            }

            System.out.println("Introdueix amplada pit");
            int amplada_pit = sc.nextInt();
            
            if(amplada_pit > 15){
                amplada_pit = 15;
                System.out.println("Amplada pit: 15.");
            }
            else if(amplada_pit < 10){
                amplada_pit = 10;
                System.out.println("Amplada pit: 10.");
            }

            conexio.updateArticle(id, nom, familia, talla_coll, amplada_pit, preu_base, iva, stock); //quan faci merge amb el develop no hi haura error
        }
        else if(familia == 2){
            
            System.out.println("Introdueix talla cintura");
            int talla_cintura = sc.nextInt();
           
            if(talla_cintura > 56){
                talla_cintura = 56;
                System.out.println("Talla cintura: 56.");
            }
            else if(talla_cintura < 24){
                talla_cintura = 24;
                System.out.println("Talla cintura: 24.");
            }

            System.out.println("Introdueix llargada camal");
            int llargada_camal = sc.nextInt();
            
            if(llargada_camal > 46){
                llargada_camal = 46;
                System.out.println("Llargada camal: 46.");
            }
            else if(llargada_camal < 32){
                llargada_camal = 32;
                System.out.println("Llargada camal: 32.");
            }

            conexio.updateArticle(id, nom, familia, talla_cintura, llargada_camal, preu_base, iva, stock);  //quan faci el merge quedara correcte ja que en el origin develop el familia es int
            menu();
        }
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
        menu();
    }

    public void esborrarArticle(){
        System.out.println("Vols eliminar un article.");
        System.out.println("");
        System.out.print("Introdueix el ID del article a eliminar: ");
        int id = sc.nextInt();

        conexio.deleteArticle(id);
        menu();
    }

    public void menuVendes(){
        String dni;
        int IDArticle;
        int seguirComprant;
        int quantitatProducte;
        int maximArticles = 0;
        ArrayList<Integer> idArticles = new ArrayList<>();
        ArrayList<Integer> quantitats = new ArrayList<>();

        try {
            ResultSet rs = conexio.numArticles();
            if(rs.next()){
                maximArticles = rs.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
            System.out.println("Error en obtenir el nombre d'articles: " + e.getMessage());
            return;
        }

        System.out.println("Vols fer comprar.");
        do {
            System.out.println("Introdueix el seu DNI:");
            dni = sc.next();
        } while (!demanarDNI(dni));

        do {
            do {
                conexio.selectArticles();
                System.out.println("===============");
                System.out.println("Intodueix el ID del article a comprar");
            IDArticle = controlInt();
            }while (IDArticle <= 0 || IDArticle > maximArticles);
            do {
                System.out.println("Quants del article vols comprar?");
                quantitatProducte = controlInt();
            } while (quantitatProducte <= 0);
            do {
                System.out.println("Vols seguir comprant? 1.SI. 2.NO.");
                seguirComprant = controlInt();
            } while (seguirComprant > 2 || seguirComprant < 1);
            idArticles.add(IDArticle);
            quantitats.add(quantitatProducte);
        } while (seguirComprant != 2);
        
        ventes.insertVenta(dni, idArticles, quantitats);
        menu();
    }

    public void recercaPerDNI(){
        String dni;
        do {
            System.out.print("Introdueix el DNI del usuari el qual vols consultar:");
            dni = sc.next();
        } while (!demanarDNI(dni));
        vendesPerClient.consultaVendesClient(dni);
        menu();
    }

    public void recercaPerID(){
        int id;
            
        System.out.print("Introdueix el ID del producte el cual vols fer recerca:");
        id = controlInt();
        ventesArticle.mostrarPerArticle(id);
        menu();
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

    public boolean controlFamilia(int familia){
        if(familia == 1 || familia == 2){
            return true;
        }
        else{
            return false;
        }
    }

    public int controlInt(){
        int num;
        try {
            num = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Ha de ser numero enter");
            num = -100;
            return num;
        }
        return num;
    }


}
