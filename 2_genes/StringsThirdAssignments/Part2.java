public class Part2 {
    public double cgRatio(String dna) {
        int total = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.toUpperCase().charAt(i) == 'C' || dna.toUpperCase().charAt(i) == 'G') {
                total++;
            }
         }
         return (double) total / dna.length();
    }
    
    public void testcgRatio() {
        String dna = "ATGCCATAG";
        System.out.println("DNA strand: " + dna + "\nCG ratio: "  + cgRatio(dna)); // 4/9
    }
    
    public int countCTG(String dna) {
        int total = 0;
        int start = 0;
        while(true) {
            int index = dna.toUpperCase().indexOf("CTG", start); 
            if (index == -1) {
                break;
            }
            total++;
            start = index + 3;
        }
        return total;
    }
    
    public void testCountCTG() {
        String dna = "CAATGCTGATCTGATAAT";
        System.out.println("DNA strand: " + dna + "\nNumber of times CTG appears in DNA: "  + countCTG(dna)); // 2
     }

}
