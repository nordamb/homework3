package junior;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        //todo Задача №1
        Car zhiguly = new Zhiguly();
        Car toyota = new Toyota();
        zhiguly.start();
        toyota.start();
        zhiguly.stop();
        toyota.stop();
        zhiguly.lights();
        toyota.lights();
        zhiguly.feature();
        toyota.feature();

        //todo Задача №2
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Main.class.getClassLoader().getResource("my_first_file.txt").getPath()))) {
            String row = bufferedReader.readLine() + " " + bufferedReader.readLine();
            System.out.println(row);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //todo Задача №3
        FinancialRecord financialRecord = new FinancialRecord(500, 300);
        try (FileWriter fileWriter = new FileWriter(Main.class.getClassLoader().getResource("record.txt").getPath())) {
            fileWriter.write(financialRecord.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
