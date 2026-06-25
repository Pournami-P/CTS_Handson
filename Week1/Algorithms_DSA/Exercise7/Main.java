//Exercise 7: Financial Forecasting
import java.util.Scanner;

public class Main {

    static double forecast(double value, double rate, int years) {
        if (years == 0)
            return value;
        return forecast(value * (1 + rate / 100), rate, years - 1);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Present Value: ");
        double presentValue = sc.nextDouble();

        System.out.print("Enter Growth Rate (%): ");
        double growthRate = sc.nextDouble();

        System.out.print("Enter Number of Years: ");
        int years = sc.nextInt();

        double futureValue = forecast(presentValue, growthRate, years);

        System.out.printf("Future Value = %.2f", futureValue);

        sc.close();
    }
}