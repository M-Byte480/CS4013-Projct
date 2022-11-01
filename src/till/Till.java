package till;

public class Till {

    private  double startOfEachDay = 200;


    Till() {
    }

    public double getStartOfEachDay() {
        return startOfEachDay;
    }

    public double sale(double moneyMadeFromSale) {
        double dailyProfit = startOfEachDay;
        dailyProfit += moneyMadeFromSale;
        return dailyProfit;
    }

    public double cashPaid(double moneyMadeFromSale, double cashPaid) {
        double change;
        change = cashPaid - moneyMadeFromSale;
        return change;

    }
}
