package Persistance;

import Model.Currency;
import Model.ExchangeRate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ExchangeRateLoaderFromWeb implements ExchangeRateLoader {

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest?base=" +
                                from.getCode());
            URLConnection conn = url.openConnection();
            return new ExchangeRate(from, to, 
                    getNumberFromConnection(conn, to));
            
        } 
        catch (MalformedURLException x) {} 
        catch (IOException x) {}
        return new ExchangeRate(from, to, 0.0);
    }
    
    private double getNumberFromConnection(URLConnection conn, Currency to){
        String line;
        try (BufferedReader reader = 
                new BufferedReader(
                        new InputStreamReader(conn.getInputStream()))) {
                line = reader.readLine();
                int i = line.indexOf(to.getCode())+5;
                int f = line.indexOf(",", i);
                line = line.substring(i, f);
        } catch (Exception x){
            return 1.0;
        }
        return Double.parseDouble(line);
    }
}
