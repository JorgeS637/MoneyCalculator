package View;

import Model.Currency;
import Model.CurrencyList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainFrame extends JFrame {

    private JLabel ltitle, lquantity, lto;
    private JTextField tquantity, tres;
    private JComboBox cbfrom, cbto;
    private JButton bconver;
    
    public MainFrame() {
        this.setTitle("MoneyCalculator");
        this.setSize(700,200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        initComponents();
        situateComponents();
    }
    
    private void initComponents() {
        ltitle = new JLabel("Currency Converter");
        lquantity = new JLabel("Quantity: ");
        lto = new JLabel(" equals: ");
        tquantity = new JTextField();
        tquantity.setColumns(7);
        cbfrom = new JComboBox();
        cbto = new JComboBox();
        tres = new JTextField();
        tres.setColumns(10);
        tres.setEditable(false);
        bconver = new JButton("Calculate");
    }
    
    private void situateComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel());
        titlePanel.add(ltitle);
        JPanel quantityPanel = new JPanel(new FlowLayout());
        quantityPanel.add(lquantity);
        quantityPanel.add(tquantity);
        quantityPanel.add(cbfrom);
        quantityPanel.add(lto);
        quantityPanel.add(tres);
        quantityPanel.add(cbto);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bconver);
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(quantityPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(panel);
    }
    
    public double getQuantity() {
        return Double.parseDouble(tquantity.getText());
    }
    
    public Currency getFrom() {
        return (Currency) cbfrom.getSelectedItem();
    }
    
    public Currency getTo() {
        return (Currency) cbto.getSelectedItem();
    }
    
    public void setExchange(double x, String code) {
        tres.setText(String.format("%.3f", x) + " " + code);
    }
    
    public void addRateListener(ActionListener buttonListener) {
        bconver.addActionListener(buttonListener);
    }
    
    public void DisplayErrorMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    public void addCurrencyList(CurrencyList currencyList) {
        for (Currency c : currencyList.getCurrencies()) {
            cbfrom.addItem(c);
            cbto.addItem(c);
        }
    }
}
