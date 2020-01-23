package Control;

import Model.CurrencyList;
import Persistance.CurrencyListLoader;
import Persistance.ExchangeRateLoaderFromWeb;
import Persistance.FileCurrencyListLoader;
import View.MainFrame;


public class Main {

    public static void main(String [] args) {
        MainFrame mainFrame = new MainFrame();
        CurrencyListLoader currencyListLoader = new FileCurrencyListLoader("currencies.txt");
        CurrencyList currencyList = currencyListLoader.load();
        ExchangeRateLoaderFromWeb exchangeRate = new ExchangeRateLoaderFromWeb();
        Controller controller = new Controller(mainFrame, currencyList, exchangeRate);
    }
}
