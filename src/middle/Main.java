package middle;

import junior.Car;
import junior.FinancialRecord;
import junior.Toyota;
import junior.Zhiguly;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final Random RANDOM = new Random(1);
    private static final int MINIMUM_SUM_RECORD = 10000;
    private static final int MAXIMUM_SUM_RECORD = 100000;

    public static void main(String[] args) {
        //todo Задача №1
        createCars(40);

        //todo Задача №2
        writeFinancialReports(getFinancialReports(10));
        readFinancialReports();
    }

    public static void createCars(int amount) {
        Car[] arrayCars = new Car[amount];
        for (int i = 0; i < arrayCars.length; i++) {
            if (i % 2 == 0) {
                arrayCars[i] = CarFactory.getZhiguly();
            } else {
                arrayCars[i] = CarFactory.getToyota();
            }
        }
        for (Car car : arrayCars) {
            if (car instanceof Zhiguly) {
                car.feature();
            } else if (car instanceof Toyota) {
                car.feature();
            }
        }
    }

    public static List<FinancialRecord> getFinancialReports(int amount) {
        List<FinancialRecord> financialRecordList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            double income = RANDOM.doubles(MINIMUM_SUM_RECORD, MAXIMUM_SUM_RECORD).findFirst().getAsDouble();
            double outcome = RANDOM.doubles(MINIMUM_SUM_RECORD, MAXIMUM_SUM_RECORD).findFirst().getAsDouble();
            financialRecordList.add(new FinancialRecord(income, outcome));
        }
        return financialRecordList;
    }

    public static void writeFinancialReports(List<FinancialRecord> financialRecordList) {
        String filePath = Main.class.getClassLoader().getResource("record.txt").getPath();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            financialRecordList.forEach(record -> {
                try {
                    bufferedWriter.write(record.toString());
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFinancialReports() {
        double resultIncome = 0;
        double resultOutcome = 0;
        String filePath = Main.class.getClassLoader().getResource("record.txt").getPath();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while (bufferedReader.ready()) {
                String row = bufferedReader.readLine();
                String[] arrayRow = row.split("([=,])");
                double income = Double.parseDouble(arrayRow[1]);
                resultIncome += income;
                double outcome = Double.parseDouble(arrayRow[3]);
                resultOutcome += outcome;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("общие доходы - " + resultIncome + ", общие расходы - " + resultOutcome);
    }
}
