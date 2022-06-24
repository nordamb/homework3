package senior;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final int YEAR = 2012;

    public static void main(String[] args) {
        //todo Задача №1
        getReportShop("pyterochka");

        //todo Задача №2
        getOutcomeShops();
    }

    public static void getReportShop(String nameShop) {
        System.out.println("Прибыль по магазину " + nameShop + " по месяцам");
        for (int i = 1; i < 13; i++) {
            readReports(getFile(i).getName(), nameShop);
        }
    }

    public static void readReports(String fileName, String nameShop) {
        double resultIncome = 0;
        double resultOutcome = 0;
        String[] arrayFileName = fileName.split("\\p{P}");
        String filePath = Paths.get("resource", fileName).toString();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while (bufferedReader.ready()) {
                String row = bufferedReader.readLine();
                String[] arrayRow = row.split(";");
                if (arrayRow[0].equalsIgnoreCase(nameShop)) {
                    double income = Double.parseDouble(arrayRow[1]);
                    resultIncome += income;
                    double outcome = Double.parseDouble(arrayRow[2]);
                    resultOutcome += outcome;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        double revenue = resultIncome - resultOutcome;
        System.out.println(String.join(".", arrayFileName[1], arrayFileName[2]) + ": " + getDecimalFormat().format(revenue));
    }

    public static void getOutcomeShops() {
        Map<String, Double> mapOutcomeShops = new HashMap<>();
        for (int i = 1; i < 13; i++) {
            for (Map.Entry<String, Double> item : getOutcomeShopsReports(getFile(i).getName()).entrySet()) {
                if (mapOutcomeShops.containsKey(item.getKey())) {
                    mapOutcomeShops.computeIfPresent(item.getKey(), (k, v) -> v += item.getValue());
                } else {
                    mapOutcomeShops.put(item.getKey(), item.getValue());
                }
            }
        }
        for (Map.Entry<String, Double> item : mapOutcomeShops.entrySet()) {
            System.out.println("Расходы " + item.getKey() + " за весь период: " + getDecimalFormat().format(item.getValue()));
        }
    }

    public static Map<String, Double> getOutcomeShopsReports(String fileName) {
        Map<String, Double> mapOutcomeShops = new HashMap<>();
        String filePath = Paths.get("resource", fileName).toString();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            //пропуск первой строки, т.к. там заголовок, ничего проще не нашел)
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                String row = bufferedReader.readLine();
                String[] arrayRow = row.split(";");
                String shop = arrayRow[0];
                double outcome = Double.parseDouble(arrayRow[2]);
                if (mapOutcomeShops.containsKey(shop)) {
                    mapOutcomeShops.computeIfPresent(shop, (k, v) -> v += outcome);
                } else {
                    mapOutcomeShops.put(shop, outcome);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mapOutcomeShops;
    }

    public static DecimalFormat getDecimalFormat() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        return new DecimalFormat("0.00", decimalFormatSymbols);
    }

    public static File getFile(int month) {
        return new File(Main.class
                .getClassLoader()
                .getResource("report_" + (month < 10 ? "0" + month : month) + "_" + YEAR + ".txt")
                .getPath());
    }
}
