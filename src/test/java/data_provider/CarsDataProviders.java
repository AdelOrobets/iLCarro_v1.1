package data_provider;

import dto.CarLombok;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarsDataProviders {

    @DataProvider(name = "addNewCarDPFromFile")
    public Iterator<CarLombok> addNewCarDPFromFile() {
        List<CarLombok> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("data_provider/cars.csv"),
                StandardCharsets.UTF_8))) {

            if (reader == null) {
                throw new FileNotFoundException("File car.csv not found in classpath");
            }

            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                if (fields.length < 10) {
                    throw new RuntimeException("Expected 10 fields in CSV line but got " + fields.length);
                }
                list.add(CarLombok.builder()
                        .city(fields[0].trim())
                        .manufacture(fields[1].trim())
                        .model(fields[2].trim())
                        .year(Integer.parseInt(fields[3].trim()))
                        .fuel(fields[4].trim())
                        .seats(Integer.parseInt(fields[5].trim()))
                        .carClass(fields[6].trim())
                        .serialNumber(fields[7].trim())
                        .pricePerDay(Double.parseDouble(fields[8].trim()))
                        .about(fields[9].trim())
                        .build());
                line = reader.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read car.csv", e);
        }
        return list.iterator();
    }

}
