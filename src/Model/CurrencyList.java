package Model;

import java.util.ArrayList;
import java.util.List;


public final class CurrencyList {

    private final ArrayList<Currency> currencyList;
    
    public CurrencyList() {
        currencyList = new ArrayList<>();
    }
    
    public List<Currency> getCurrencies() {
        return currencyList;
    }
    
    public void add(Currency currency) {
        currencyList.add(currency);
    }    
}
