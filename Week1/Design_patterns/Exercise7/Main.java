//Exercise 7: Implementing the Observer Pattern
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String stockName, double price);
}

interface Stock {
    void register(Observer o);
    void deregister(Observer o);
    void notifyObservers();
}

class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double price;

    public StockMarket(String stockName, double price) {
        this.stockName = stockName;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    public void register(Observer o)   { observers.add(o); }
    public void deregister(Observer o) { observers.remove(o); }

    public void notifyObservers() {
        for (Observer o : observers)
            o.update(stockName, price);
    }
}

class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) { this.appName = appName; }

    public void update(String stockName, double price) {
        System.out.println("[MobileApp - " + appName + "] " +
                           stockName + " price updated to $" + price);
    }
}

class WebApp implements Observer {
    private String siteName;

    public WebApp(String siteName) { this.siteName = siteName; }

    public void update(String stockName, double price) {
        System.out.println("[WebApp - " + siteName + "] " +
                           stockName + " price updated to $" + price);
    }
}

public class Main {
    public static void main(String[] args) {
        StockMarket market = new StockMarket("AAPL", 150.00);

        Observer mobile = new MobileApp("StockApp");
        Observer web    = new WebApp("finance.com");

        market.register(mobile);
        market.register(web);

        market.setPrice(155.50);
        market.setPrice(160.00);

        market.deregister(mobile);
        market.setPrice(162.00);  // only WebApp notified
    }
}