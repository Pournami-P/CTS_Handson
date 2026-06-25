//Exercise 4: Implementing the Adapter Pattern
interface PaymentProcessor {
    void processPayment(double amount);
}
class PayPalGateway {
    public void makePayment(double amount) {
        System.out.println("PayPal payment of $" + amount + " processed.");
    }
}

class StripeGateway {
    public void chargeCard(double amount) {
        System.out.println("Stripe card charge of $" + amount + " processed.");
    }
}

class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPal;

    public PayPalAdapter(PayPalGateway payPal) {
        this.payPal = payPal;
    }

    public void processPayment(double amount) {
        payPal.makePayment(amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripe;

    public StripeAdapter(StripeGateway stripe) {
        this.stripe = stripe;
    }

    public void processPayment(double amount) {
        stripe.chargeCard(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentProcessor payPal = new PayPalAdapter(new PayPalGateway());
        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());

        payPal.processPayment(150.00);
        stripe.processPayment(200.00);
    }
}