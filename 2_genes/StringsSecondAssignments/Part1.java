public class Part1 {
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
        
    public void testFindStopCodon() {
        String dna = "AATGCCGTAGTAACGT "; //DNA with ATG, TAA and the substring between them is a multiple of 3 
        int index = findStopCodon(dna, 1, "TAA");
        System.out.println("DNA strand: " + dna + "\n" + index);
        
        dna = "AATGCGTAGTAACTAA  "; //DNA with ATG, TAA and the substring between them is a multiple of 3  and contains TAA
        index = findStopCodon(dna, 1, "TAA"); 
        System.out.println("DNA strand: " + dna + "\n" + index);
        
        dna = "AATGCGTAGTTACGT"; //DNA with no TAA 
        index = findStopCodon(dna, 1, "TAA");
        System.out.println("DNA strand: " + dna + "\n" + index + "\n");
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
    
    public void testFindGene() {
        String dna = "AATGCCGTAGTAACGT "; //DNA with ATG, TAA and the substring between them is a multiple of 3 
        System.out.println("DNA strand: " + dna + "\n" + "Gene: " + findGene(dna));
        
        dna = "AATGCAGTAGTAACTAA"; //DNA with multiple stop codons (multiple of 3 )
        System.out.println("DNA strand: " + dna + "\n" + "Gene: " + findGene(dna));
        
        dna = "AATGCTAGTAACTAA  "; //DNA with multiple stop codons (not a multiple of 3 )
        System.out.println("DNA strand: " + dna + "\n" + "Gene: " + findGene(dna));
        
        dna = "AATGCGTAGTTACGT"; //DNA with no TAA 
        System.out.println("DNA strand: " + dna + "\n" + "Gene: " + findGene(dna));

        dna = "AATGCGCAGTCACGT"; //DNA with no stop codons
        System.out.println("DNA strand: " + dna + "\n" + "Gene: " + findGene(dna));
        
        dna = "ATTGCGTAGTAACGT"; //DNA with no start codon
        System.out.println("DNA strand: " + dna + "\n" + "Gene: " + findGene(dna) + "\n");

    }
    
    public void printAllGenes(String dna) {
        while (true) {
            String gene = findGene(dna);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println("DNA strand: " + dna + "\n" + "Gene: " + gene);
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
    }
    
    public void testPrintAllGenes() {
        String dna = "AATGCCGTAGTAACGT "; //DNA with ATG, TAA and the substring between them is a multiple of 3 
        printAllGenes(dna);
        
        dna = "AATGCAGTAGTAACTAA  "; //DNA with multiple stop codons (multiple of 3 )
        printAllGenes(dna);
        
        dna = "AATGCTAGTAACTAA  "; //DNA with multiple stop codons (not a multiple of 3 )
        printAllGenes(dna);
        
        dna = "AATGCGTAGTTACGT"; //DNA with no TAA 
        printAllGenes(dna);

        dna = "AATGCGCAGTCACGT"; //DNA with no stop codons
        printAllGenes(dna);
        
        dna = "ATTGCGTAGTAACGT"; //DNA with no start codon
        printAllGenes(dna);
    }
}
