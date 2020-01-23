package Model;

public class ExchangeRate {

    private final Currency from;
    private final Currency to;
    private final double ratio;

    public ExchangeRate(Currency from, Currency to, double rate) {
        this.from = from;
        this.to = to;
        this.ratio = rate;
    }

    public double getRatio() {
        return ratio;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }  
}
