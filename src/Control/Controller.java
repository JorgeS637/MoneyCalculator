package Control;

import Model.Currency;
import Model.CurrencyList;
import Persistance.ExchangeRateLoaderFromWeb;
import View.MainFrame;
import java.awt.event.*;

public class Controller {
    private MainFrame frame;
    private CurrencyList currencyList;
    private ExchangeRateLoaderFromWeb exchange;

    public Controller(MainFrame View, CurrencyList currencyList,
                                        ExchangeRateLoaderFromWeb exchangeRate) {
        this.frame = View;
        this.currencyList = currencyList;
        this.exchange = exchangeRate;
        frame.addRateListener(new RateListener());
        frame.addCurrencyList(currencyList);
        frame.setVisible(true);
    }

    private class RateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            double quantity;
            Currency from;
            Currency to;
            double rate;
            
            try {
              quantity = frame.getQuantity();
              from = frame.getFrom();
              to = frame.getTo();
              rate = exchange.load(from, to).getRatio();
              frame.setExchange(quantity * rate, to.getSymbol());
            } catch (NumberFormatException ex) {
                frame.DisplayErrorMessage("Introduce un número");
            }
        }
    }
   
}
