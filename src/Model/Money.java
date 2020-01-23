package Model;

public class Money {

    private final double quantity;
    private final Currency currency;

    public Money(double amount, Currency currency) {
        this.quantity = amount;
        this.currency = currency;
    }

    public double getQuantity() {
        return quantity;
    }

    public Currency getCurrency() {
        return currency;
    }
}
