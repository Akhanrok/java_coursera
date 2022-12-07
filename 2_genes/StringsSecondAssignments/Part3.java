public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int index  = dna.indexOf(stopCodon, startIndex + 3);  
        while (index != -1) {
            if ((index - startIndex) % 3 == 0) { //check if the substring is a multiple of 3
                return index;
            } else {
                index = dna.indexOf(stopCodon, index + 1); //update index to the index of the next TAA
            }
        }
        return dna.length();
    }

    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(Math.min(taaIndex, tgaIndex), tagIndex);
        if (minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public int countGenes(String dna) {
        int total = 0;
        while (true) {
            String currGene = findGene(dna);
            if (currGene.isEmpty()) {
                break;
            }
            total += 1;
            dna = dna.substring(dna.indexOf(currGene) + currGene.length());
        }
        return total;
    }
    
    public void testCountGenes() {
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println("DNA strand: " + dna + "\n" + "Number of genes: " + countGenes(dna));// 2 genes (ATGTAA and ATGCCCTAG)
    }    
}
