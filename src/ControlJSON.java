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
public ArrayList<Producte> llegirProductes() {
    carregarJSON();
    ArrayList<Producte> productes = new ArrayList<>();
    JSONArray articles = (JSONArray) producte;

    for (int j = 0; j < articles.size(); j++) {
        JSONObject article = (JSONObject) articles.get(j);
        int id = ((Long) article.get("id")).intValue();
        String nom = (String) article.get("nom");
        String familia = (String) article.get("familia");
        double preuBase = (double) article.get("preu_base");
        int iva = ((Long) article.get("iva")).intValue();
        int stock = ((Long) article.get("stock")).intValue();

        if (familia.equals("pantaló")) {
            int tallaCintura = ((Long) article.get("talla_cintura")).intValue();
            int llargadaCamal = ((Long) article.get("llargada_camal")).intValue();
            productes.add(new Pantalo(id, nom, familia, preuBase, iva, stock, tallaCintura, llargadaCamal));
        } else {
            int tallaColl = ((Long) article.get("talla_coll")).intValue();
            int ampladaPit = ((Long) article.get("amplada_pit")).intValue();
            productes.add(new Camisa(id, nom, familia, preuBase, iva, stock, tallaColl, ampladaPit));
        }
    }
    return productes;
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