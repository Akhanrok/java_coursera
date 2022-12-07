public class Part3 {
    public boolean twoOccurrences(String stringA, String stringB) {
        int index1 = stringB.indexOf(stringA);
        int index2 = stringB.indexOf(stringA, index1 + 1);
        if (index2 != -1) {
            return true;
        } else {
            return false;
        }
    }
    
    public String  lastPart(String stringA, String stringB) {
        int index1 = stringB.indexOf(stringA);
        if (index1 != -1) {
            return stringB.substring(index1 + stringA.length());
        } else {
            return stringB;
        }
    }
    
    public void testing() {
        String stringA;
        String stringB;
        
        stringA = "by";
        stringB = "A story by Abby Long";
        System.out.println(stringA + " - " + stringB + "\n" + twoOccurrences(stringA, stringB) + "\n"); //true
        
        stringA = "a";
        stringB = "banana";
        System.out.println(stringA + " - " + stringB + "\n" + twoOccurrences(stringA, stringB) + "\n"); //true
        
        stringA = "atg";
        stringB = "ctgtatgta";
        System.out.println(stringA + " - " + stringB + "\n" + twoOccurrences(stringA, stringB) + "\n"); //false
        
        stringA = "an";
        stringB = "banana";
        System.out.println(stringA + " - " + stringB + "\n" + lastPart(stringA, stringB) + "\n"); //ana
        
        stringA = "zoo";
        stringB = "forest";
        System.out.println(stringA + " - " + stringB + "\n" + lastPart(stringA, stringB) + "\n"); //forest
    }
}