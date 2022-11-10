package till;

import reservation.Invoice;
import restaurant.Util;

import java.io.File;
import java.io.IOException;

public class Till {
    private  double startOfEachDay = 200;

    public Till() {
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
        if (cashPaid < moneyMadeFromSale){
            double extraToPay = moneyMadeFromSale - cashPaid;
            System.out.println("You owe an extra " + extraToPay + " euro");
        }
        change = cashPaid - moneyMadeFromSale;
        return change;
    }

    //closing table
    //calculate price
    public void billTable(char type, double amount, Table table) throws IOException {
        switch (type) {
            case 1:
                type = 'C';
                table.closeTable();
                sendLog("Paid with Credit");
                Invoice.sendInvoice();
                sale(amount); //invoice amount


                break;


            case 2:
                type = 'D';
                table.closeTable();
                sendLog("Paid with Debit");
                Invoice.sendInvoice();
                sale(amount); //invoice amount

                break;


            case 3:
                type = 'X';
                table.closeTable();
                Invoice.sendInvoice();
                sendLog("Paid with cash");
                sale(amount); //invoice amount
                cashPaid(amount, ); //invoice amount + amount paid in cash

                break;
        }
    }

    private void sendLog(String whatHappened) throws IOException {
        Util writeToLog = new Util(new File("src/data/log.csv"));

        writeToLog.addDataToFile(whatHappened);

        writeToLog.close();
    }
}
