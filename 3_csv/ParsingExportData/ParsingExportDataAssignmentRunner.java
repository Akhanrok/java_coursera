import edu.duke.*;
import org.apache.commons.csv.*;
public class ParsingExportDataAssignmentRunner {
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String currentCountry = record.get("Country");
            if (currentCountry.equalsIgnoreCase(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                String result = "exports: " + exports + "; value: " + value;
                return result;
            }
        }
        return "no result";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
         for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int total = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                total++;
            }
        }
        return total;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String currentAmount = record.get("Value (dollars)");
            if (currentAmount.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country + " " + currentAmount);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        
        CSVParser parser = fr.getCSVParser();
        System.out.println("Macedonia:" + "\n" + countryInfo(parser, "Macedonia")); //information about the country
        
        parser = fr.getCSVParser();
        System.out.println("Nepal:" + "\n" + countryInfo(parser, "Nepal")); //no information about the country. 
        
        parser = fr.getCSVParser();
        System.out.println("Export of copper and zinc:");
        listExportersTwoProducts(parser, "copper", "zinc"); //countries that export both items
        
        parser = fr.getCSVParser();
        System.out.println("Export of tea:");
        System.out.println(numberOfExporters(parser, "tea")); //number of countries that export the item
        
        parser = fr.getCSVParser();
        System.out.println("> $3,421,000,00:");
        bigExporters(parser, "$3,421,000,000"); //countries whose export value string is longer than the length of "$3,421,000,000"
    }

}
