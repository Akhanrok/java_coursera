import edu.duke.*;
public class Part4 {
    public static void findLinks() {
        URLResource urlr = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        System.out.println("Youtube links: ");
        for (String link : urlr.lines()) {
            int youtubeIndex = link.toLowerCase().indexOf("youtube.com");
            if (youtubeIndex != -1) {
                int startIndex = link.lastIndexOf("\"", youtubeIndex);
                int stopIndex = link.indexOf("\"", youtubeIndex + 1);
                System.out.println(link.substring(startIndex + 1, stopIndex));
            }
        }
    }

    public static void main(String[] args) {
        Part4 urls = new Part4();
        urls.findLinks();
    }
}
