package Persistance;

import Model.Currency;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import Model.CurrencyList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;

//funcionaria si se le poner una key que no caduque

public class CurrencyLoaderURL implements CurrencyListLoader {
    
    @Override
    public CurrencyList load() {
        CurrencyList currencyList = new CurrencyList();
        Gson gson = new Gson();
        try {
            URL url = new URL("https://free.currconv.com/api/v7/currencies?apiKey=1f5e721121618d26434f");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = (JsonArray) jsonParser.parse(fixJson(line)).getAsJsonObject().get("results");
            for (JsonElement e : jsonArray){
                JsonObject jsonObj = e.getAsJsonObject();
                for (String id : jsonObj.keySet()){
                    JsonObject jsonCurrency = (JsonObject) jsonObj.get(id);
                    currencyList.add(getCurrencyFromJsonObject(jsonCurrency));
                }
            }
            return currencyList;
        } catch (MalformedURLException x) {
            return null;
        } catch (IOException x) {
            return null;
        }
    }
    
    private String fixJson(String JSON) {
        String fJson = JSON.substring(11, JSON.length()-1);
        fJson= "{\"results\":" + "[" + fJson + "]}";
        return fJson;
    }
    
    private Currency getCurrencyFromJsonObject(JsonObject jsonCurrency) {
        String currencyName = jsonCurrency.get("currencyName").toString().replaceAll("\"","");
        String currencyID = jsonCurrency.get("id").toString().replaceAll("\"","");
        String currencySymbol;
        try{ currencySymbol =jsonCurrency.get("currencySymbol").toString().replaceAll("\"",""); }
        catch(Exception e){ currencySymbol = "?";}
        return new Currency(currencyID, currencyName,currencySymbol);
    }
}
