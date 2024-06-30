import java.util.ArrayList;
import java.util.Scanner;

public class CurrencyConverter {

    private static ArrayList<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose base currency");
            printCurrencyOptions();
            int baseChoice = sc.nextInt();

            System.out.println("Choose target currency");
            printCurrencyOptions();
            int targetChoice = sc.nextInt();

            System.out.println("Enter amount");
            double amount = sc.nextDouble();

            Currency baseCurrency = getCurrency(baseChoice);
            Currency targetCurrency = getCurrency(targetChoice);

            if (baseCurrency != null && targetCurrency != null) {
                double convertedAmount = convert(baseCurrency, targetCurrency, amount);
                String result = String.format("%.2f %s = %.2f %s", amount, baseCurrency.name, convertedAmount, targetCurrency.name);
                System.out.println(result);
                addToHistory(result);
            } else {
                System.out.println("Invalid choice");
            }

            System.out.println("\nConversion History:");
            for (String entry : history) {
                System.out.println(entry);
            }

            System.out.println("\nDo you want to perform another conversion? (yes/no)");
            sc.nextLine(); // consume newline
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("no")) {
                exit = true;
            }
        }

        sc.close();
    }

    private static void printCurrencyOptions() {
        System.out.println("1. Rupee");
        System.out.println("2. Dollar");
        System.out.println("3. Euro");
        System.out.println("4. Pound");
        System.out.println("5. Dirham");
        System.out.println("6. Peso");
        System.out.println("7. Yen");
        System.out.println("8. Yuan");
        System.out.println("9. Rial");
    }

    private static Currency getCurrency(int choice) {
        switch (choice) {
            case 1: return new Rupee();
            case 2: return new Dollar();
            case 3: return new Euro();
            case 4: return new Pound();
            case 5: return new Dirham();
            case 6: return new Peso();
            case 7: return new Yen();
            case 8: return new Yuan();
            case 9: return new Rial();
            default: return null;
        }
    }

    private static void addToHistory(String entry) {
        history.add(entry);
    }

    private static double convert(Currency base, Currency target, double amount) {
        double amountInRupees = amount * base.exchangeRate;
        return amountInRupees / target.exchangeRate;
    }

    static abstract class Currency {
        protected double exchangeRate;
        protected String name;
    }

    static class Rupee extends Currency {
        public Rupee() {
            this.name = "Rupee";
            this.exchangeRate = 1.0;
        }
    }

    static class Dollar extends Currency {
        public Dollar() {
            this.name = "Dollar";
            this.exchangeRate = 280.05;
        }
    }

    static class Euro extends Currency {
        public Euro() {
            this.name = "Euro";
            this.exchangeRate = 310.34;
        }
    }

    static class Pound extends Currency {
        public Pound() {
            this.name = "Pound";
            this.exchangeRate = 355.01;
        }
    }

    static class Dirham extends Currency {
        public Dirham() {
            this.name = "Dirham";
            this.exchangeRate = 75.00;
        }
    }

    static class Peso extends Currency {
        public Peso() {
            this.name = "Peso";
            this.exchangeRate = 70.00;
        }
    }

    static class Yen extends Currency {
        public Yen() {
            this.name = "Yen";
            this.exchangeRate = 1.93;
        }
    }

    static class Yuan extends Currency {
        public Yuan() {
            this.name = "Yuan";
            this.exchangeRate = 26.00;
        }
    }

    static class Rial extends Currency {
        public Rial() {
            this.name = "Rial";
            this.exchangeRate = 505.01;
        }
    }
}
