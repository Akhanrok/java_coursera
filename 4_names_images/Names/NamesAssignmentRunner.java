import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class NamesAssignmentRunner {
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int boyNames = 0;
        int girlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                boyNames++;
            } else {
                totalGirls += numBorn;
                girlNames++;
            }
        }
        System.out.println("Total girls births: " + totalGirls);
        System.out.println("Total girls names: " + girlNames);
        System.out.println("Total boys births: " + totalBoys);
        System.out.println("Total boys names: " + boyNames);
        System.out.println("Total births: " + totalBirths);
        System.out.println("Total names: " + (boyNames + girlNames));
    }

    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(String name, String gender, FileResource fr) {
        int rank = 0;
        boolean recFound = false;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currentName = rec.get(0);
            String currentGender = rec.get(1);
            if (currentGender.equals(gender)) {
                rank++;
                if (currentName.equals(name)) {
                    recFound = true;
                    break;
                }
            }
        }
        if (recFound) {
            return rank;
        } else {
            return -1;
        }
    }
    
    public void testGetRank() {
        FileResource fr = new FileResource();
        String name = "Mason";
        String gender = "M";
        int rank = getRank(name, gender, fr);
        System.out.println("Name: " + name + "; rank: " + rank);
    }
    
    public String getName(int rank, String gender, FileResource fr) {
        String name = "NO NAME";
        int currentRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currentGender = rec.get(1);
            if (currentGender.equals(gender)) {
                currentRank++;
                if (currentRank == rank) {
                    name = rec.get(0);
                    break;
                }
            }
        }
        return name;
    }
    
    public void testGetName() {
        FileResource fr = new FileResource();
        int rank = 2;
        String gender = "M";
        String name = getName(rank, gender, fr);
        System.out.println("Rank: " + rank + "; name: " + name);
    }
    
    public void whatIsNameInYear(String name, String gender, FileResource fr1, FileResource fr2) {
        int currentYearRank = getRank(name, gender, fr1);
        String newName = getName(currentYearRank, gender, fr2);
        System.out.println(name + " would be " + newName);
    }
    
    public void testWhatIsNameInYear() {
        FileResource fr1 = new FileResource();
        FileResource fr2 = new FileResource();
        String name = "Isabella";
        String gender = "F";
        whatIsNameInYear(name, gender, fr1, fr2);
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int year = Integer.MIN_VALUE;
        int rank = Integer.MAX_VALUE;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRank(name, gender, fr);
            if (currentRank != -1 && currentRank < rank) {
                rank = currentRank;
                year = currentYear;
            }
        } 
        if (year == Integer.MIN_VALUE) {
            return -1;
        } else {
            return year;
        }
    }
    
    public void testYearOfHighestRank() {
        String name = "Mason";
        String gender = "M";
        System.out.println("Name: " + name + "; year of highest rank: " + yearOfHighestRank(name, gender));
    }
    
    public double getAverageRank(String name, String gender) {
        double totalRank = 0;
        int recordCount = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRank(name, gender, fr);
            if (currentRank != -1) {
                totalRank += currentRank;
                recordCount++;
            }
        } 
        if (recordCount == 0) {
            return -1.0;
        } else {
            return totalRank / recordCount;
        }
    }
    
    public void testGetAverageRank() {
        String name = "Mason";
        String gender = "M";
        System.out.println("Name: " + name + "; average rank: " + getAverageRank(name, gender));
    }
    
    public int getTotalBirthsRankedHigher(String name, String gender, FileResource fr) {
        int rank = getRank(name, gender, fr);
        int totalBirths = 0;
        int currentRank = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            String currentGender = record.get(1);
            if (currentGender.equals(gender)) {
                currentRank++;
                if (currentRank < rank) {
                    int currentBirths = Integer.parseInt(record.get(2));
                    totalBirths += currentBirths;
                } else {
                    break;
                }
            }
        }
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        FileResource fr = new FileResource();
        String name = "Ethan";
        String gender = "M";
        System.out.println("Name: " + name + "; number of births (names ranked higher): " + getTotalBirthsRankedHigher(name, gender, fr));
    }
}
