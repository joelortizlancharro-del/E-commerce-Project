import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import utils.ConnexioBD;

public class App {
    static Connection conn;
    static ConnexioBD conexio=null;
    Scanner sc = new Scanner(System.in);
      
    ControlJSON json = new ControlJSON();


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
        System.out.print("Que vols fer?");
        int num = sc.nextInt(); 
        opcions(num); //Aqui cridem el switch
    }

    public void opcions(int num){ //Aixo es el switch

        switch (num) {
            case 1:
                inicialitzarBD();
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
}
