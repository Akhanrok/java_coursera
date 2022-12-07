public class Part2 {
    public int howMany(String stringA, String stringB) {
        int total = 0;
        int start = 0;
        while (true) {
            int index = stringB.indexOf(stringA, start);
            if (index == -1) {
                break;
            }
            total++;
            start = index + stringA.length();
        }
        return total;
    }
    
    public void testHowMany() {
        String stringA = "GAA";
        String stringB = "ATGAACGAATTGAATC";
        System.out.println(stringA + " - " + stringB + "\n" + howMany(stringA, stringB)); //3

        stringA = "AA";
        stringB = "ATAAAA";
        System.out.println(stringA + " - " + stringB + "\n" + howMany(stringA, stringB)); //2
    }
}
