import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public class ControlJSON {


    private Object producte;
    public static void main(String[] args) {
        ControlJSON p = new ControlJSON();
        p.principal();
    }

    public void principal(){

    }
    public String llegirProductes(int num){
        carregarJSON();
        Producte newProducte = new Producte();
        ArrayList<Producte> productes = new ArrayList<>();
        int id;
        String nom;
        String familia;
        int talla_cintura;
        int llargada_camal;
        double preu_base;
        int iva;
        int stock;
        int talla_coll;
        int amplada_pit;
        JSONArray articles = (JSONArray) producte;
        num = 0;
        for(int j = 0; j < articles.size(); j++){
            JSONObject article = (JSONObject) articles.get(j);
            id = (int) article.get("id");
            nom = (String) article.get("nom");
            familia = (String) article.get("familia");
            preu_base = (double) article.get("preu_base");
            iva = (int) article.get("iva");
            if(familia.equals("pantaló")){
                talla_cintura = (int) article.get("talla_cintura");
                llargada_camal = (int) article.get("talla_camal");
            }
            else{
                talla_coll = (int) article.get("talla_coll");
                amplada_pit = (int) article.get("amplada_pit");
            }
        }
    }

    public void carregarJSON(){
        JSONParser parser = new JSONParser();
        producte = null;
        try {
            producte = parser.parse(new FileReader("PE11_articles.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    
}