import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class ParsingWeatherDataAssignmentRunner {
    private static String TEMPERATURE = "TemperatureF";
    private static String HUMIDITY = "Humidity";
    private static String DATE = "DateUTC";
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser) {
            double temperatureF = Double.parseDouble(currentRow.get(TEMPERATURE));
            if (coldestSoFar == null && temperatureF != -9999) {
                coldestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get(TEMPERATURE));
                double coldestTemp = Double.parseDouble(coldestSoFar.get(TEMPERATURE));
                if (currentTemp < coldestTemp && temperatureF != -9999) {
                    coldestSoFar = currentRow;
                }
            }
        }
        return coldestSoFar;
    }
    
     public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature: " + coldestRecord.get(TEMPERATURE) + "; time: " + coldestRecord.get(DATE));
    }
    
    public File fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        File file = null;
        CSVRecord coldestSoFar = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
                file = f;
            } else {
                double coldestTemperature = Double.parseDouble(coldestSoFar.get(TEMPERATURE));
                double currentTemperature = Double.parseDouble(currentRow.get(TEMPERATURE));
                if (currentTemperature < coldestTemperature) {
                    coldestSoFar = currentRow;
                    file = f;
                }
            }
        }
        return file;
    }
    
     public void getAll(CSVParser parser) {
        for (CSVRecord record : parser) {
            System.out.println(record.get(DATE) + ": " + record.get(TEMPERATURE));
        }
    }
    
    public void testFileWithColdestTemperature() {
        File file = fileWithColdestTemperature();
        System.out.println("File with the coldest temperature: " + file.getName());
        FileResource fr = new FileResource(file);
        String coldestTemperature = coldestHourInFile(fr.getCSVParser()).get(TEMPERATURE);
        System.out.println("Coldest temperature in " + file.getName() + ": " + coldestTemperature);
        System.out.println("All the temperatures in " + file.getName() + ":");
        getAll(fr.getCSVParser());
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            String humidityStr = currentRow.get(HUMIDITY);
            if (!humidityStr.equals("N/A")) {
                int humidity = Integer.parseInt(humidityStr);
                if (lowestSoFar == null) {
                    lowestSoFar = currentRow;
                } else {
                    int lowestHumidity = Integer.parseInt(lowestSoFar.get(HUMIDITY));
                    if (humidity < lowestHumidity) {
                        lowestSoFar = currentRow;
                    }
                }
            }
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidityRecord = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity: " + lowestHumidityRecord.get(HUMIDITY) + "; time: " + lowestHumidityRecord.get(DATE));
    }
   
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else {
                int lowestHumidity = Integer.parseInt(lowestSoFar.get(HUMIDITY));
                int currentHumidity = Integer.parseInt(currentRow.get(HUMIDITY));
                if (currentHumidity < lowestHumidity) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRecord = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity: " + lowestHumidityRecord.get(HUMIDITY) + "; time: " + lowestHumidityRecord.get(DATE));
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double totalTemperature = 0;
        int recordCount = 0;
        for (CSVRecord record : parser) {
            double currentTemperature = Double.parseDouble(record.get(TEMPERATURE));
            if (currentTemperature != -9999) {
                totalTemperature += currentTemperature;
                recordCount++;
            }
        }
        return totalTemperature / recordCount;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double averageTemperature = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature: " + averageTemperature);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemperature = 0;
        int recordCount = 0;
        for (CSVRecord record : parser) {
            int currentHumidity = Integer.parseInt(record.get(HUMIDITY));
            if (currentHumidity >= value) {
                double currentTemperature = Double.parseDouble(record.get(TEMPERATURE));
                if (currentTemperature != -9999) {
                    totalTemperature += currentTemperature;
                    recordCount++;
                }
            }
        }
        return totalTemperature / recordCount;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double averageTemperature = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (Double.isNaN(averageTemperature)) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temperature when high humidity: " + averageTemperature);
        }
    }
}
