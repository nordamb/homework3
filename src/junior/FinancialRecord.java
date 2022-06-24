package junior;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FinancialRecord {
    private double income;
    private double outcome;

    public FinancialRecord(double income, double outcome) {
        this.income = income;
        this.outcome = outcome;
    }

    public double getIncomes() {
        return income;
    }

    public void setIncomes(double income) {
        this.income = income;
    }

    public double getOutcome() {
        return outcome;
    }

    public void setOutcome(double outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("0.00", decimalFormatSymbols);
        return "доходы = " + decimalFormat.format(income) +", расходы = " +
                decimalFormat.format(outcome);
    }
}
