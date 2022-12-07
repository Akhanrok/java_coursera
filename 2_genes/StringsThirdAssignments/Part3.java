import edu.duke.*;
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int index = 0;
        while(true) {
            index = dna.toUpperCase().indexOf(stopCodon, startIndex + 3);
            if (index == -1 || (index - startIndex) % 3 == 0) {
                break;
            }
            startIndex += 3;
        }
        if (index != -1) {
            return index;
        } else {
            return dna.length();            
        }
    }
    
    public String findGene(String dna, int start) {
        final String START_CODON = "ATG";
        int startIndex = dna.toUpperCase().indexOf(START_CODON, start);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(Math.min(taaIndex, tgaIndex), tagIndex);
        if (minIndex == dna.length()) {
            return "";
        } else {
            return dna.substring(startIndex, minIndex + 3);
        }
    }
    
    public StorageResource getAllGenes(String dna) {
        int start = 0;
        StorageResource genes = new StorageResource();
        while (true) {
            String gene = findGene(dna, start);
            if (gene.isEmpty()) {
                break;
            }
            genes.add(gene);
            dna = dna.substring(dna.indexOf(gene, start) + gene.length());
        }
        return genes;
    }
    
    public double cgRatio(String dna) {
        int total = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.toUpperCase().charAt(i) == 'C' || dna.toUpperCase().charAt(i) == 'G') {
                total++;
            }
         }
         return (double) total / dna.length();
    }
    
    private void processGenes(StorageResource sr) {
        int lengthTotal = 0;
        int cgRatioTotal = 0;
        int maxLength = Integer.MIN_VALUE;
        for (String gene : sr.data()) {
            int currentLength = gene.length();
            double cgRatio = cgRatio(gene);
            if (currentLength > 9) {
                lengthTotal++;
            }
            if (cgRatio > 0.35) {
                cgRatioTotal++;
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        System.out.println("Number of genes: " + sr.size());
        System.out.println("Number of genes longer than 9 characters: " + lengthTotal);
        System.out.println("Number of genes with CG ratio higher than 0.35: " + cgRatioTotal);               
        System.out.println("Length of the longest DNA strand: " + maxLength + "\n");
    }
     public void testProcessGenes() {
        //FileResource fr = new FileResource();
        //String dna = fr.asString();
        //StorageResource geneList = getAllGenes(dna);
        //processGenes(geneList);
        
        String dna = "ATGCGTCCAACCTAACGATGGCGAACTGAA"; //genes longer than 9 characters
        System.out.println("DNA strand: " + dna);
        StorageResource genes = getAllGenes(dna);
        processGenes(genes);
        
        dna = "ATGACCTAACGATGGCGTGAA"; //no genes longer than 9 characters
        System.out.println("DNA strand: " + dna);
        genes = getAllGenes(dna);
        processGenes(genes);
        
        dna = "ATGCCCTAACGATGGCGCCCTGAA"; //genes with CG ratio higher than 0.35
        System.out.println("DNA strand: " + dna);
        genes = getAllGenes(dna);
        processGenes(genes);
        
        dna = "ATGAAATAACGATGATATATTGAA"; //genes with CG ratio lower than 0.35
        System.out.println("DNA strand: " + dna);
        genes = getAllGenes(dna);
        processGenes(genes);
    }
}
