package till;

public class Till {

    private static double startOfEachDay = 200;

    Till(double amount) {
        this.startOfEachDay = amount;
    }

    public double getStartOfEachDay() {
        return startOfEachDay;
    }

    public double sale(double moneyMadeFromSale) {
        double dailyProfit = startOfEachDay;
        dailyProfit += moneyMadeFromSale;
        return dailyProfit;
    }

}
