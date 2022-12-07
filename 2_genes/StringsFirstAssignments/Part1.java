public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) { //no ATG
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if (stopIndex == -1) { // no TAA
            return "";
        }
        if ((stopIndex - startIndex) % 3 == 0) {
            result = dna.substring(startIndex, stopIndex + 3);
        }
        return result;
    }
    
    public void testSimpleGene() {
        String dna = "AATGCCGTAGTAACGT"; //DNA with ATG, TAA and the substring between them is a multiple of 3
        System.out.println("DNA strand: " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene: " + gene);
        
        dna = "AATGCGTAGTAACGT"; //DNA with ATG, TAA and the substring between them is not a multiple of 3
        System.out.println("DNA strand: " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene: " + gene);
        
        dna = "AATGCGTAGTTACGT"; //DNA with ATG and with no TAA
        System.out.println("DNA strand: " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene: " + gene);
        
        dna = "ACTGCGTAGTAACGT"; //DNA with TAA and no ATG 
        System.out.println("DNA strand: " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene: " + gene);
        
        dna = "ACTGCGTAGTTACGT"; //DNA with no ATG and no TAA
        System.out.println("DNA strand: " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene: " + gene);
    }
}
